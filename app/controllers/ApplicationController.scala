package controllers

import enums.CandidateType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.mvc._
import services.{RegistrationService, ValidationService}
import utils.Logging
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class ApplicationController extends Controller with Logging {

  def registerCustomer = Action.async { implicit request => {
    try {
      val registerRequest = ValidationService.validateRegistration(request)
      RegistrationService.register(registerRequest, CandidateType.CUSTOMER.toString)
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

  def registerSupplier = Action.async { implicit request => {
    try {
      val registerRequest = ValidationService.validateRegistration(request)
      RegistrationService.register(registerRequest, CandidateType.SUPPLIER.toString)
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
