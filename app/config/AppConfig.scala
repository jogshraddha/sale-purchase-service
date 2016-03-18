package config

import utils.Logging


object AppConfig extends AppConfig {

}

trait AppConfig extends Logging {
  val saleRecordIdPrefix = "SO"
  val purchaseRecordIdPrefix = "PO"
}
