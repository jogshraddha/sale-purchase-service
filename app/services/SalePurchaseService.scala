package services

import java.util.Date
import play.api.Logger

import controllers.SaleRequest
import exceptions.{ErrorCode, SalePurchaseServiceException}
import models.Candidate
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.http.Status._
import utils.RecordIdGenerator

object SalePurchaseService {

  def addRecord(saleRequest: SaleRequest, recordType: String) = {
    RegistrationService.registrations(saleRequest.email) match {
      case Some(x: Candidate) => {
        val record_id = RecordIdGenerator.generateRecordId(recordType)
        Logger.debug(s"Record with id ${record_id} has been generated")
        val submitted_date = new Date()
        val amount = saleRequest.quantiry * saleRequest.rate
        DB.withConnection { implicit connection =>
          SQL("""INSERT INTO Record
       (recordid, record_type, transportation_cost, email, submitted_date, quantity, rate, amount)
        values ({record_id}, {record_type}, {transportation_cost}, {email}, {submitted_date}, {quantity}, {rate}, {amount})""")
            .on(
              "record_id" -> record_id,
              "record_type" -> recordType,
              "transportation_cost" -> saleRequest.transport_cost,
              "email" -> saleRequest.email,
              "submitted_date" -> submitted_date,
              "quantity" -> saleRequest.quantiry,
              "rate" -> saleRequest.rate,
              "amount" -> amount
            )
            .executeInsert()
        }
      }
      case _ => {
        throw new SalePurchaseServiceException(NOT_FOUND,
          s"${saleRequest.email} Please register this candidate",
          ErrorCode.RegistrationNotFound())
      }
    }
  }
}
