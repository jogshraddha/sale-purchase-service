package utils

import play.api.Logger

trait Logging {

  lazy val logger = Logger("application")

}
