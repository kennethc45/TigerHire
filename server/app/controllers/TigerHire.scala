package controllers

import javax.inject._

import play.api.mvc._
import play.api.i18n._
import play.api.data._ 
import play.api.data.Forms._
import models._
import scala.concurrent.Future

import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

case class LoginData(username: String, password: String)
case class ApplicationData(aId: Int, jId: Int, answer1: String, answer2: String, answer3: String, experience: String)
case class InviteData(aId: Int, jId: Int)

@Singleton
class TigerHire @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
    extends MessagesAbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile]{

  private val model = new TigerHireModel(db)
  
  def index = Action {
    Ok(views.html.index())
  }

  val loginForm = Form(mapping(
    "Username" -> text(3, 10), 
    "Password" -> text(8)
    )(LoginData.apply)(LoginData.unapply))

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def logout = Action {
    Redirect(routes.TigerHire.login).withNewSession
  }

  def recruiterLogin = Action { implicit request =>
    Ok(views.html.recruiterLogin(loginForm))
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"$username logged in with $password.")
  }

  def validateApplicantPost = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head 
      val password = args("password").head 
      model.validateApplicant(username,password).map { userExists =>
        if(userExists){
          Redirect(routes.TigerHire.jobPostList).withSession("username" -> username)
        } else {
          Redirect(routes.TigerHire.login).flashing("error" -> "Invalid username/password combination.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.login)))
  }

  def validateRecruiterPost = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head 
      val password = args("password").head 
      model.validateRecruiters(username,password).map { userExists =>
        if(userExists){
          Redirect(routes.TigerHire.profileList).withSession("username" -> username)
        } else {
          Redirect(routes.TigerHire.login).flashing("error" -> "Invalid username/password combination.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.login)))
  }

  // def validateLoginForm = Action { implicit request =>
  //   loginForm.bindFromRequest.fold(
  //     formWithError => BadRequest(views.html.login(formWithError)),
  //     ld => 
  //       if(models.TigerHireModel.validateUser(ld.username,ld.password)) {
  //       Redirect(routes.TigerHire.profile).withSession("username" -> ld.username)
  //       } else {
  //       Redirect(routes.TigerHire.login).flashing("error" -> "Invalid username/password combination.")
  //       }
  //   )
  // }

  
  def createUser = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head
      val password = args("password").head
      model.createUser(username, password).map { userExists =>
        if(userExists){
          Redirect(routes.TigerHire.createUser).withSession("username" -> username)
        }else {
          Redirect(routes.TigerHire.login).flashing("error" ->"User creation failed.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.login)))
  }

  // def createUser = Action { implicit request => 
  //       val postVals = request.body.asFormUrlEncoded
  //       postVals.map { args =>
  //         val username = args("username").head 
  //         val password = args("password").head 
  //         if(models.TigerHireModel.createUser(username,password)) {
  //           Redirect(routes.TigerHire.profile).withSession("username" -> username)
  //         } else {
  //           Redirect(routes.TigerHire.login).flashing("error" -> "User creation failed.")
  //         }
  //       }.getOrElse(Redirect(routes.TigerHire.login))
  // }

 def jobPostList = Action.async { implicit request =>
    model.getJobs().map { jobs => 
      Ok(views.html.home(jobs))
    }
  }

  // def rjobPostList = Action.async { implicit request =>
  //   model.getJobs().map { jobs => 
  //           println("Getting jobs page")
  //           Ok(views.html.rhome(jobs))
  //       }//.getOrElse(Redirect(routes.TigerHire.login))
  // }
    
   def searchJobTitle = Action.async { implicit request =>
    val query = request.getQueryString("search").getOrElse("")
    model.searchJobTitle(query).map { jobPosts => 
        Ok(views.html.home(jobPosts))
      }
    }

  //  def searchJobTitle = Action { implicit request =>
  //    val query = request.getQueryString("search").getOrElse("")
  //    val company = List("Mastercard","Visa","Paypal")
  //    val location = List("Morrisville, NC","Austin, TX","Denver, CO")
  //    val remoteType = List("Hybrid", "Remote", "On-site")
  //    val jobTitles = List("Software Engineer", "Data Intern","Software Engineer")
  //    val employment = List("Full-time","Part-time","Part-time")
  //    val filteredJobTitles = jobTitles.filter(_.toLowerCase.contains(query.toLowerCase))
  //    val usernameOption = request.session.get("username")
  //    usernameOption.map { username =>
  //      val jobPosts = List("Software Engineer","Data Engineer","Software Engineer")

  //      Ok(views.html.home(jobPosts, filteredJobTitles,company,location,remoteType,employment))
  //    }.getOrElse(Redirect(routes.TigerHire.login))
  //  }

  def profilee = Action.async { implicit request =>
    val username = request.session.get("username").getOrElse("tjarrett")
    model.getProfile(username).map { profile => 
            Ok(views.html.profile(profile))
        }
    }

  def rprofile = Action.async { implicit request =>
    val username = request.session.get("username").getOrElse("tjarrett")
    model.getRProfile(username).map { profile => 
            Ok(views.html.rprofile(profile))
        }
    }
  //   val name = "Mark Lewis"
  //   val pronouns = "He/Him"
  //   val bio = "Simulator of planetary rings, Scala zealot, avid roller skater, and general lover of programming and technology."
  //   val education = "B.S. Computer Science -- Trinity University \n PhD, Roller Derbying -- RollerCade University"
  //   val experience = "Professor at Trinity University -- 27 Years Senior Software Engineer -- Amazon Professor at Trinity University -- 4 months"

  //   Ok(views.html.profile(name, pronouns, bio, education, experience))
  // }

//   def favorites = TODO

 def job(id: Int) = Action.async { implicit request =>
    model.getJob(id).map { job => 
            Ok(views.html.job(job(0)))
        }
    }

  val applicationForm = Form(mapping(
  "aId" -> number, 
  "jId" -> number,
  "answer1" -> text,
  "answer2" -> text,
  "answer3" -> text,
  "experience" -> text
  )(ApplicationData.apply)(ApplicationData.unapply))

  def applicationPage(id: Int) = Action.async { implicit request =>
    model.getJob(id).map { job =>
      Ok(views.html.applicationPage(job(0)))
    }
  }

  def submitApplication = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val aId = args("aId").head
      val jId = args("jId").head
      val answer1 = args("answer1").head
      val answer2 = args("answer2").head
      val answer3 = args("answer3").head
      val experience = args("experience").head
      model.createApplication(Some(aId.toInt), Some(jId.toInt), Some(answer1), Some(answer2), Some(answer3), Some(experience)).map { submitted =>
        if(submitted){
          Redirect(routes.TigerHire.jobPostList)
        } else {
          Redirect(routes.TigerHire.login).flashing("error" ->"Couldn't submit application.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.jobPostList)))
  }

  def submitJob = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val salary = args("salary").head
      val location = args("location").head
      val remote = args("remote").head
      val hours = args("hours").head
      val name = args("name").head
      val cId = args("cId").head
      val answer1 = args("question1").head
      val answer2 = args("question2").head
      val answer3 = args("question3").head
      val experience = args("description").head
      model.createJob(Some(salary), Some(location), Some(remote), Some(hours), name, Some(cId.toInt), Some(answer1), Some(answer2), Some(answer3), Some(experience)).map { submitted =>
        if(submitted){
          Redirect(routes.TigerHire.profileList)
        } else {
          Redirect(routes.TigerHire.createJobPage).flashing("error" ->"Couldn't submit job.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.createJobPage)))
  }

  // def company = Action.async {
  //   // val name = "Mastercard"
  //   // val purpose = "We work to connect and power an inclusive digital economy that benefits everyone, everywhere by making transactions safe, simple, smart and accessible. Using secure data and networks, partnerships and passion, our innovations and solutions help individuals, financial institutions, governments and businesses realize their greatest potential. Our decency quotient, or DQ, drives our culture and everything we do inside and outside of our company."
  //   // val companyType = "Payment Processing and Technology"
  //   // val dateFounded = "1966"

  //       model.getJobs().map { jobs => 
  //       println("Getting jobs page")
  //       Ok(views.html.company(jobs))
  //       }//.getOrElse(Redirect(routes.TigerHire.login))

  // }

  def viewCompany(cId: Int) = Action.async { implicit request =>
  model.getJobsBycId(cId).flatMap { jobs =>
  model.getCompanyInfo(cId).map{  info =>
        Ok(views.html.company(jobs,info))
  } .recover {
    case _ => Redirect(routes.TigerHire.login)
  }

  }
}

// def companyFiltered = Action.async { implicit request =>
//   val companyId = re
  
//   getJobsByCompanyId(companyId).map { jobs =>
//     println("Getting jobs page")
//     Ok(views.html.company(jobs))
//   }.recover {
//     case _ => Redirect(routes.TigerHire.login)
//   }
// }

  def createJobPage = Action {
   Ok(views.html.createJobPage())
  }

  def inbox = Action.async { implicit request =>
    val username = request.session.get("username").getOrElse("tjarrett")
    model.getInboxJobs(username).map { jobs => 
      Ok(views.html.inbox(username, jobs))
    }
  }

  def rinbox = Action.async { implicit request =>
    val username = request.session.get("username").getOrElse("tjarrett")
    model.getApplicantList().map { applicantList => 
      Ok(views.html.rinbox(username, applicantList))
    }
  }

  // val inviteForm = Form(mapping(
  // "aId" -> number, 
  // "jId" -> number
  // )(InviteData.apply)(InviteData.unapply))

  def sendInvite() = Action.async { implicit request => 
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val jId = args("jId").head
      val aId = args("aId").head
      model.sendInvite(Some(aId.toInt), Some(jId.toInt)).map { submitted =>
        if(submitted){
          Redirect(routes.TigerHire.profileList)
        } else {
          Redirect(routes.TigerHire.login).flashing("error" ->"Couldn't submit invite.")
        }
      }
    }.getOrElse(Future.successful(Redirect(routes.TigerHire.profileList)))
  }

  

//   def sendMessage = TODO
     //    Action { implicit request =>val usernameOption = request.session.get("username")
  //   usernameOption.map{ username => 
  //     val postVals = request.body.asFormUrlEncoded
  //     postVals.map { args =>
  //       val user = args("user").head
  //       val message = args("newPrivateMessage").head
  //       models.TigerHireModel.addPrivateMessage(user, s"$username: $message");
  //       Redirect(routes.TigerHire.inbox)
  //     }.getOrElse(Redirect(routes.TigerHire.inbox))
  //   }.getOrElse(Redirect(routes.TigerHire.inbox))
  //}

//   def validateLogin = TODO
//   //= Action.async { implicit request => }


  def newUser = Action { 
    Ok(views.html.newUser()) 
  }

  def rApplication = Action.async { implicit request =>
    val username = request.session.get("username").getOrElse("tjarrett")
    model.rApplications(username).map { applicantList => 
      Ok(views.html.rapplication(applicantList))
    }
  }

  def profileList = Action.async { implicit request =>
    model.profileList().map { profiles =>
      Ok(views.html.rhome(profiles))  
    }
  }

}
