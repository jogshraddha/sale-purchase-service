package controllers

import java.util.Date

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

case class SaleRequest(
                         email: String,
                         transport_cost: Double,
                         submitted_date: Date,
                         quantiry: Double,
                         rate: Double
)

object SaleRequest {
  implicit val saleReads: Reads[SaleRequest] = (
    (JsPath \ "email").read[String] and
      (JsPath \ "transport_cost").read[Double] and
      (JsPath \ "submitted_date").read[Date] and
      (JsPath \ "quantiry").read[Double] and
      (JsPath \ "rate").read[Double]
    )(SaleRequest.apply _)
}