package controllers
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

case class CandidateRegisterReq(
                             firstname:String,
                             lastname:String,
                             address:String,
                             phone:String,
                             email:String)

object CandidateRegisterReq {
  implicit val candidateRegisterReReads: Reads[CandidateRegisterReq] = (
    (JsPath \ "firstname").read[String] and
      (JsPath \ "lastname").read[String] and
      (JsPath \ "address").read[String] and
      (JsPath \ "phone").read[String] and
      (JsPath \ "email").read[String]
    )(CandidateRegisterReq.apply _)
}
