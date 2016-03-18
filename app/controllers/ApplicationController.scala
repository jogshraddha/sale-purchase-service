package controllers

import enums.CandidateType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import play.api.mvc._
import play.api.libs.json._
import services.{UserDetailsService, RegistrationService, ValidationService}
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

  def getCustomer(id: String) = Action.async { implicit request => {
   try {
     Future(Ok(Json.toJson(UserDetailsService.getUser(id, CandidateType.CUSTOMER.toString))))
   } catch {
     case e: Throwable => Future {
       logger.error("Internal server error : ", e)
       InternalServerError(ErrorCode.Error("Internal server error").toJson)
     }
   }
  }}

  def getCustomers = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(UserDetailsService.getUsers(CandidateType.CUSTOMER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getSupplier(id: String) = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(UserDetailsService.getUser(id, CandidateType.SUPPLIER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getSuppliers = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(UserDetailsService.getUsers(CandidateType.SUPPLIER.toString))))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}

  def getAllCandidates = Action.async { implicit request => {
    try {
      Future(Ok(Json.toJson(UserDetailsService.getAllCandidates)))
    } catch {
      case e: Throwable => Future {
        logger.error("Internal server error : ", e)
        InternalServerError(ErrorCode.Error("Internal server error").toJson)
      }
    }
  }}
}
