package models

import java.util.Date

import play.api.libs.json._
import anorm._
import anorm.SqlParser._

case class Record(recordid: String,
                 record_type: String,
                 transportation_cost: Double,
                 email: String,
                 submitted_date: Date,
                 quantity: Double,
                 rate: Double,
                 amount: Double)

object Record {
  val parser: RowParser[Record] = {
    str("recordid") ~
      str("record_type") ~
      double("transportation_cost") ~
      str("email") ~
      date("submitted_date") ~
      double("quantity") ~
      double("rate") ~ double("amount") map {
      case recordid ~ record_type ~ transportation_cost ~ email ~ submitted_date ~ quantity ~ rate ~ amount =>
        Record(recordid, record_type, transportation_cost, email, submitted_date, quantity, rate, amount)
    }
  }

  implicit val RecordWrites = new Writes[Record] {
    def writes(record: Record) = Json.obj(
      "recordid" -> record.recordid,
      "record_type" -> record.record_type,
      "transportation_cost" -> record.transportation_cost,
      "email" -> record.email,
      "submitted_date" -> record.submitted_date,
      "quantity" -> record.quantity,
      "rate" -> record.rate,
      "amount" -> record.amount
    )
  }
}
