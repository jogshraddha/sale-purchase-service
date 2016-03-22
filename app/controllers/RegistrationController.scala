package controllers

import javax.inject.Inject

import enums.CandidateType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.mvc._
import play.api.libs.json._
import services.{UserDetailsService, RegistrationService, ValidationService}
import utils.Logging
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class RegistrationController @Inject() (registrationService: RegistrationService, validationService: ValidationService, userDetailsService: UserDetailsService) extends Controller with Logging {

  def registerCustomer = Action.async { implicit request => {
    try {
      val registerRequest = validationService.validateRegistration(request)
      registrationService.register(registerRequest, CandidateType.CUSTOMER.toString)
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
      val registerRequest = validationService.validateRegistration(request)
      registrationService.register(registerRequest, CandidateType.SUPPLIER.toString)
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

  def getCustomer(id: String) = Action.async { implicit request => {
   try {
     Future(Ok(Json.toJson(userDetailsService.getUser(id, CandidateType.CUSTOMER.toString))))
   } catch {
     case e: Throwable => Future {
       logger.error("Internal server error : ", e)
       InternalServerError(ErrorCode.Error("Internal server error").toJson)
     }
   }
  }}

  def getCustomers = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(userDetailsService.getUsers(CandidateType.CUSTOMER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getSupplier(id: String) = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(userDetailsService.getUser(id, CandidateType.SUPPLIER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getSuppliers = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(userDetailsService.getUsers(CandidateType.SUPPLIER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getAllCandidates = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(userDetailsService.getAllCandidates)))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}
}
