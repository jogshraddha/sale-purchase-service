package services

import enums.CandidateType
import exceptions.{ErrorCode, SalePurchaseServiceException}
import anorm._
import play.api.Play.current
import play.api.Logger
import play.api.db.DB
import play.api.http.Status._

object UserDetailsService {
  def getUser(id: String, role: String) = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM Candidate r WHERE r.email = {emailAddress} and r.role= {role}")
        .on("emailAddress" -> id, "role" -> role).as(models.Candidate.parser.singleOpt)
    }
  }

  def getUsers(role: String) = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM Candidate where role={role}").on("role" -> role).as(models.Candidate.parser.*)
    }
  }

  def getAllCandidates = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM Candidate").as(models.Candidate.parser.*)
    }
  }
}
