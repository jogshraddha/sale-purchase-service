package services

import java.util.Locale

import controllers.{CandidateRegisterReq, SaleRequest}
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.libs.json.{Json, JsError, JsSuccess, JsValue}
import play.api.mvc.{Request, AnyContent}
import play.api.http.Status._

class ValidationService {
  def validateRegistration(request: Request[AnyContent]): CandidateRegisterReq = {
    var bodyJson: JsValue = null
    request.contentType.map(_.toLowerCase(Locale.ENGLISH)) match {
      case Some("application/json") => {
        bodyJson = request.body.asJson.get
        bodyJson.validate[CandidateRegisterReq] match {
          case s: JsSuccess[CandidateRegisterReq] => {
            val customerRegisterReq = s.get
            customerRegisterReq
          }
          case e: JsError => {
            throw new SalePurchaseServiceException(BAD_REQUEST,
              s"Error while parsing the request - ${Json.stringify(JsError.toJson(e))}",
              ErrorCode.Error("Bad Request"))
          }
        }
      }
    }
  }

  def validateSaleRequest(request: Request[AnyContent]): SaleRequest = {
    var bodyJson: JsValue = null
    request.contentType.map(_.toLowerCase(Locale.ENGLISH)) match {
      case Some("application/json") => {
        bodyJson = request.body.asJson.get
        bodyJson.validate[SaleRequest] match {
          case s: JsSuccess[SaleRequest] => {
            val saleReq = s.get
            saleReq
          }
          case e: JsError => {
            throw new SalePurchaseServiceException(BAD_REQUEST,
              s"Error while parsing the request - ${Json.stringify(JsError.toJson(e))}",
              ErrorCode.Error("Bad Request"))
          }
        }
      }
    }
  }
}
