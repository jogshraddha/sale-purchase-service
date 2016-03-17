package exceptions

import utils.Logging
import play.api.http.Status._

case class SalePurchaseServiceException(errorMsg: String, ec: ErrorCode) extends Exception(errorMsg: String) with Logging {

  def this(status: Int, errorMsg: String, ec: ErrorCode) = {
    this(status.toString, ec)
    logger.error(errorMsg)
  }

  def this(status: Int, t: Throwable, ec: ErrorCode) = {
    this(status.toString, ec)
    logger.error(t.getMessage)
  }

  def this(status: Int, ec: ErrorCode) = {
    this(status.toString, ec)
    logger.error(ec.message)
  }

  def this(ec: ErrorCode) = {
    this(INTERNAL_SERVER_ERROR.toString, ec)
    logger.error(ec.message)
  }

  def getStatus: Int = {
    new Integer(super.getMessage)
  }
}
