package models

import java.util.Date

import play.api.libs.json._
import anorm._
import anorm.SqlParser._

case class Candidate(firstname:String,
                         lastname:String,
                         address: String,
                         phone: String,
                         email: String,
                         role: String)

object Candidate {
  val parser: RowParser[Candidate] = {
    str("firstname") ~
      str("lastname") ~
      str("address") ~
      str("phone") ~
      str("email") ~
      str("role")  map {
      case firstname ~ lastname ~ address ~ phone ~ email ~ role =>
        Candidate(firstname, lastname, address, phone, email, role)
    }
  }

  implicit val CandidateWrites = new Writes[Candidate] {
    def writes(candidate: Candidate) = Json.obj(
      "firstname" -> candidate.firstname,
      "lastname" -> candidate.lastname,
      "address" -> candidate.address,
      "phone" -> candidate.phone,
      "email" -> candidate.email,
      "role" -> candidate.role
    )
  }
}
