package exceptions

import play.api.libs.json._

trait ErrorCode {
  val code: Int
  val message: String

  def toJson:JsValue = {
    JsObject(Seq(
      "message" -> JsString(message),
      "code" -> JsNumber(code)))
  }
}


object ErrorCode {

  case class Error (message: String) extends ErrorCode { val code = 600 }

  //Registration
  case class RegistrationEmailInvalidFormat() extends ErrorCode {
    val message = "Please provide email address in valid format"
    val code = 601
  }
  case class RegistrationPhoneInvalidFormat() extends ErrorCode {
    val message = "Please provide phone number in valid format"
    val code = 602
  }
  case class RegistrationAlreadyActivated() extends ErrorCode {
    val message = "You are already registered! Please press SIGN IN"
    val code = 603
  }
  case class RegistrationAlreadyExpired() extends ErrorCode {
    val message = "Your registration has expired"
    val code = 604
  }
  case class RegistrationAlreadyInUse() extends ErrorCode {
    val message = "This email has already registered!"

    val code = 605
  }
  case class RegistrationNotFound() extends ErrorCode {
    val message = "Not a valid registration"
    val code = 606
  }
  case class RegistrationNotActivated() extends ErrorCode {
    val message = "Registration is not yet activated"
    val code = 607
  }
}
