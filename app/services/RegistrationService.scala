package services

import java.util.Date

import controllers.CandidateRegisterReq
import exceptions.{ErrorCode, SalePurchaseServiceException}
import anorm._
import play.api.Play.current
import play.api.Logger
import play.api.db.DB
import play.api.http.Status._

object RegistrationService{
  def register(registerRequest: CandidateRegisterReq, role: String) = {
    checkForExistingRegistration(registerRequest.email)
    DB.withConnection { implicit connection =>
      SQL("""INSERT INTO Candidate
       (firstname, lastname, address, phone, email, role)
        values ({firstname}, {lastname}, {address}, {phone}, {email}, {role})""")
        .on(
          "firstname" -> registerRequest.firstname,
          "lastname" -> registerRequest.lastname,
          "address" -> registerRequest.address,
          "phone" -> registerRequest.phone,
          "email" -> registerRequest.email,
          "role" -> role
        )
        .executeInsert()
    }
  }

  def checkForExistingRegistration(emailAddress: String) = {
    registrations(emailAddress) match {
      case Some(existingRegistration) => {
          throw new SalePurchaseServiceException(FORBIDDEN,
            s"${emailAddress} email is already registered",
            ErrorCode.RegistrationAlreadyInUse())
      }
      case None => Logger.debug(s"No registration exists with email ${emailAddress}")
    }
  }

  def registrations(emailAddress: String): Option[models.Candidate] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM Candidate r WHERE r.email = {emailAddress}")
        .on("emailAddress" -> emailAddress).as(models.Candidate.parser.singleOpt)
    }
  }

}
