package utils

import _root_.anorm.SqlParser
import _root_.anorm._
import config.AppConfig
import enums.RecordType
import play.api.Play.current
import play.api.db.DB

object RecordIdGenerator extends AppConfig{

  def generateRecordId(recordType: String): String = {
    val count : Long = fetchHits(recordType) + 1
    var recordId: String = ""
    if(RecordType.withName(recordType) == RecordType.SALE) {
      recordId = saleRecordIdPrefix +"-"+ count
    } else if(RecordType.withName(recordType) == RecordType.PURCHASE) {
      recordId = purchaseRecordIdPrefix +"-"+ count
    }
    recordId
  }

  def fetchHits(recordType: String): Long = {
    DB.withConnection { implicit connection =>
      val hits = SQL("SELECT COUNT(1) AS HITS FROM Record where record_type = {recordType}")
        .on("recordType" -> {
          recordType
        }).as(SqlParser.long("HITS").single)
      hits
    }
  }
}
