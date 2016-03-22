package controllers

import javax.inject.Inject

import enums.RecordType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.mvc._
import play.api.libs.json._
import services.{RegistrationService, SalePurchaseService, ValidationService}
import utils.Logging
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class SalePurchaseController @Inject() (salePurchaseService: SalePurchaseService, validationService: ValidationService) extends Controller with Logging {
  def addSaleRecord = Action.async { implicit request => {
    try {
      val saleRequest = validationService.validateSaleRequest(request)
      salePurchaseService.addRecord(saleRequest, RecordType.SALE.toString)
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
      val purchaseRequest = validationService.validateSaleRequest(request)
      salePurchaseService.addRecord(purchaseRequest, RecordType.PURCHASE.toString)
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
