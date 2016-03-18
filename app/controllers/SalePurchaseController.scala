package controllers

import enums.RecordType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.mvc._
import play.api.libs.json._
import services.{SalePurchaseService, ValidationService}
import utils.Logging
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class SalePurchaseController extends Controller with Logging {
  def addSaleRecord = Action.async { implicit request => {
    try {
      val saleRequest = ValidationService.validateSaleRequest(request)
      SalePurchaseService.addRecord(saleRequest, RecordType.SALE.toString)
      Future(Created)
    } catch {
      case e: SalePurchaseServiceException => Future {
        Status(e.getStatus)(e.ec.toJson)
      }
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def addPurchaseRecord = Action.async { implicit request => {
    try {
      val purchaseRequest = ValidationService.validateSaleRequest(request)
      SalePurchaseService.addRecord(purchaseRequest, RecordType.PURCHASE.toString)
      Future(Created)
    } catch {
      case e: SalePurchaseServiceException => Future {
        Status(e.getStatus)(e.ec.toJson)
      }
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}
}
