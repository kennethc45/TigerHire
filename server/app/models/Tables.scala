package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends Tables {
  val profile = slick.jdbc.PostgresProfile
}

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Appl.schema, Applicants.schema, AProfile.schema, Company.schema, Inbox.schema, Items.schema, Jobs.schema, Recruiters.schema, RProfile.schema, Users.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Appl
   *  @param aId Database column a_id SqlType(int4), Default(None)
   *  @param jId Database column j_id SqlType(int4), Default(None)
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param answer1 Database column answer1 SqlType(varchar), Length(5000,true), Default(None)
   *  @param answer2 Database column answer2 SqlType(varchar), Length(5000,true), Default(None)
   *  @param answer3 Database column answer3 SqlType(varchar), Length(5000,true), Default(None)
   *  @param experience Database column experience SqlType(varchar), Length(5000,true), Default(None) */
  case class ApplRow(aId: Option[Int] = None, jId: Option[Int] = None, id: Int, answer1: Option[String] = None, answer2: Option[String] = None, answer3: Option[String] = None, experience: Option[String] = None)
  /** GetResult implicit for fetching ApplRow objects using plain SQL queries */
  implicit def GetResultApplRow(implicit e0: GR[Option[Int]], e1: GR[Int], e2: GR[Option[String]]): GR[ApplRow] = GR{
    prs => import prs._
    ApplRow.tupled((<<?[Int], <<?[Int], <<[Int], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table appl. Objects of this class serve as prototypes for rows in queries. */
  class Appl(_tableTag: Tag) extends profile.api.Table[ApplRow](_tableTag, "appl") {
    def * = (aId, jId, id, answer1, answer2, answer3, experience).<>(ApplRow.tupled, ApplRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((aId, jId, Rep.Some(id), answer1, answer2, answer3, experience)).shaped.<>({r=>import r._; _3.map(_=> ApplRow.tupled((_1, _2, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column a_id SqlType(int4), Default(None) */
    val aId: Rep[Option[Int]] = column[Option[Int]]("a_id", O.Default(None))
    /** Database column j_id SqlType(int4), Default(None) */
    val jId: Rep[Option[Int]] = column[Option[Int]]("j_id", O.Default(None))
    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column answer1 SqlType(varchar), Length(5000,true), Default(None) */
    val answer1: Rep[Option[String]] = column[Option[String]]("answer1", O.Length(5000,varying=true), O.Default(None))
    /** Database column answer2 SqlType(varchar), Length(5000,true), Default(None) */
    val answer2: Rep[Option[String]] = column[Option[String]]("answer2", O.Length(5000,varying=true), O.Default(None))
    /** Database column answer3 SqlType(varchar), Length(5000,true), Default(None) */
    val answer3: Rep[Option[String]] = column[Option[String]]("answer3", O.Length(5000,varying=true), O.Default(None))
    /** Database column experience SqlType(varchar), Length(5000,true), Default(None) */
    val experience: Rep[Option[String]] = column[Option[String]]("experience", O.Length(5000,varying=true), O.Default(None))

    /** Foreign key referencing Applicants (database name appl_a_id_fkey) */
    lazy val applicantsFk = foreignKey("appl_a_id_fkey", aId, Applicants)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Jobs (database name appl_j_id_fkey) */
    lazy val jobsFk = foreignKey("appl_j_id_fkey", jId, Jobs)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Appl */
  lazy val Appl = new TableQuery(tag => new Appl(tag))

  /** Entity class storing rows of table Applicants
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true)
   *  @param jId Database column j_id SqlType(int4), Default(None) */
  case class ApplicantsRow(id: Int, username: String, password: String, jId: Option[Int] = None)
  /** GetResult implicit for fetching ApplicantsRow objects using plain SQL queries */
  implicit def GetResultApplicantsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[ApplicantsRow] = GR{
    prs => import prs._
    ApplicantsRow.tupled((<<[Int], <<[String], <<[String], <<?[Int]))
  }
  /** Table description of table applicants. Objects of this class serve as prototypes for rows in queries. */
  class Applicants(_tableTag: Tag) extends profile.api.Table[ApplicantsRow](_tableTag, "applicants") {
    def * = (id, username, password, jId).<>(ApplicantsRow.tupled, ApplicantsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), jId)).shaped.<>({r=>import r._; _1.map(_=> ApplicantsRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
    /** Database column j_id SqlType(int4), Default(None) */
    val jId: Rep[Option[Int]] = column[Option[Int]]("j_id", O.Default(None))

    /** Foreign key referencing Jobs (database name applicants_j_id_fkey) */
    lazy val jobsFk = foreignKey("applicants_j_id_fkey", jId, Jobs)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Applicants */
  lazy val Applicants = new TableQuery(tag => new Applicants(tag))

  /** Entity class storing rows of table AProfile
   *  @param description Database column description SqlType(varchar), Length(2000,true)
   *  @param education Database column education SqlType(varchar), Length(50,true)
   *  @param name Database column name SqlType(varchar), Length(50,true)
   *  @param university Database column university SqlType(varchar), Length(30,true)
   *  @param email Database column email SqlType(varchar), Length(20,true)
   *  @param pronouns Database column pronouns SqlType(varchar), Length(50,true)
   *  @param aId Database column a_id SqlType(int4), Default(None) */
  case class AProfileRow(description: String, education: String, name: String, university: String, email: String, pronouns: String, aId: Option[Int] = None)
  /** GetResult implicit for fetching AProfileRow objects using plain SQL queries */
  implicit def GetResultAProfileRow(implicit e0: GR[String], e1: GR[Option[Int]]): GR[AProfileRow] = GR{
    prs => import prs._
    AProfileRow.tupled((<<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[Int]))
  }
  /** Table description of table a_profile. Objects of this class serve as prototypes for rows in queries. */
  class AProfile(_tableTag: Tag) extends profile.api.Table[AProfileRow](_tableTag, "a_profile") {
    def * = (description, education, name, university, email, pronouns, aId).<>(AProfileRow.tupled, AProfileRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(description), Rep.Some(education), Rep.Some(name), Rep.Some(university), Rep.Some(email), Rep.Some(pronouns), aId)).shaped.<>({r=>import r._; _1.map(_=> AProfileRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column description SqlType(varchar), Length(2000,true) */
    val description: Rep[String] = column[String]("description", O.Length(2000,varying=true))
    /** Database column education SqlType(varchar), Length(50,true) */
    val education: Rep[String] = column[String]("education", O.Length(50,varying=true))
    /** Database column name SqlType(varchar), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))
    /** Database column university SqlType(varchar), Length(30,true) */
    val university: Rep[String] = column[String]("university", O.Length(30,varying=true))
    /** Database column email SqlType(varchar), Length(20,true) */
    val email: Rep[String] = column[String]("email", O.Length(20,varying=true))
    /** Database column pronouns SqlType(varchar), Length(50,true) */
    val pronouns: Rep[String] = column[String]("pronouns", O.Length(50,varying=true))
    /** Database column a_id SqlType(int4), Default(None) */
    val aId: Rep[Option[Int]] = column[Option[Int]]("a_id", O.Default(None))

    /** Foreign key referencing Applicants (database name a_profile_a_id_fkey) */
    lazy val applicantsFk = foreignKey("a_profile_a_id_fkey", aId, Applicants)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table AProfile */
  lazy val AProfile = new TableQuery(tag => new AProfile(tag))

  /** Entity class storing rows of table Company
   *  @param headquarters Database column headquarters SqlType(varchar), Length(50,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(50,true), Default(None)
   *  @param purpose Database column purpose SqlType(varchar), Length(200,true), Default(None)
   *  @param companyType Database column company_type SqlType(varchar), Length(50,true), Default(None)
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey */
  case class CompanyRow(headquarters: Option[String] = None, name: Option[String] = None, purpose: Option[String] = None, companyType: Option[String] = None, id: Int)
  /** GetResult implicit for fetching CompanyRow objects using plain SQL queries */
  implicit def GetResultCompanyRow(implicit e0: GR[Option[String]], e1: GR[Int]): GR[CompanyRow] = GR{
    prs => import prs._
    CompanyRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<[Int]))
  }
  /** Table description of table company. Objects of this class serve as prototypes for rows in queries. */
  class Company(_tableTag: Tag) extends profile.api.Table[CompanyRow](_tableTag, "company") {
    def * = (headquarters, name, purpose, companyType, id).<>(CompanyRow.tupled, CompanyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((headquarters, name, purpose, companyType, Rep.Some(id))).shaped.<>({r=>import r._; _5.map(_=> CompanyRow.tupled((_1, _2, _3, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column headquarters SqlType(varchar), Length(50,true), Default(None) */
    val headquarters: Rep[Option[String]] = column[Option[String]]("headquarters", O.Length(50,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(50,varying=true), O.Default(None))
    /** Database column purpose SqlType(varchar), Length(200,true), Default(None) */
    val purpose: Rep[Option[String]] = column[Option[String]]("purpose", O.Length(200,varying=true), O.Default(None))
    /** Database column company_type SqlType(varchar), Length(50,true), Default(None) */
    val companyType: Rep[Option[String]] = column[Option[String]]("company_type", O.Length(50,varying=true), O.Default(None))
    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table Company */
  lazy val Company = new TableQuery(tag => new Company(tag))

  /** Entity class storing rows of table Inbox
   *  @param aId Database column a_id SqlType(int4), Default(None)
   *  @param jId Database column j_id SqlType(int4), Default(None) */
  case class InboxRow(aId: Option[Int] = None, jId: Option[Int] = None)
  /** GetResult implicit for fetching InboxRow objects using plain SQL queries */
  implicit def GetResultInboxRow(implicit e0: GR[Option[Int]]): GR[InboxRow] = GR{
    prs => import prs._
    InboxRow.tupled((<<?[Int], <<?[Int]))
  }
  /** Table description of table inbox. Objects of this class serve as prototypes for rows in queries. */
  class Inbox(_tableTag: Tag) extends profile.api.Table[InboxRow](_tableTag, "inbox") {
    def * = (aId, jId).<>(InboxRow.tupled, InboxRow.unapply)

    /** Database column a_id SqlType(int4), Default(None) */
    val aId: Rep[Option[Int]] = column[Option[Int]]("a_id", O.Default(None))
    /** Database column j_id SqlType(int4), Default(None) */
    val jId: Rep[Option[Int]] = column[Option[Int]]("j_id", O.Default(None))

    /** Foreign key referencing Applicants (database name inbox_a_id_fkey) */
    lazy val applicantsFk = foreignKey("inbox_a_id_fkey", aId, Applicants)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Jobs (database name inbox_j_id_fkey) */
    lazy val jobsFk = foreignKey("inbox_j_id_fkey", jId, Jobs)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Inbox */
  lazy val Inbox = new TableQuery(tag => new Inbox(tag))

  /** Entity class storing rows of table Items
   *  @param itemId Database column item_id SqlType(serial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int4), Default(None)
   *  @param text Database column text SqlType(varchar), Length(2000,true), Default(None) */
  case class ItemsRow(itemId: Int, userId: Option[Int] = None, text: Option[String] = None)
  /** GetResult implicit for fetching ItemsRow objects using plain SQL queries */
  implicit def GetResultItemsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]]): GR[ItemsRow] = GR{
    prs => import prs._
    ItemsRow.tupled((<<[Int], <<?[Int], <<?[String]))
  }
  /** Table description of table items. Objects of this class serve as prototypes for rows in queries. */
  class Items(_tableTag: Tag) extends profile.api.Table[ItemsRow](_tableTag, "items") {
    def * = (itemId, userId, text).<>(ItemsRow.tupled, ItemsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), userId, text)).shaped.<>({r=>import r._; _1.map(_=> ItemsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(serial), AutoInc, PrimaryKey */
    val itemId: Rep[Int] = column[Int]("item_id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int4), Default(None) */
    val userId: Rep[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column text SqlType(varchar), Length(2000,true), Default(None) */
    val text: Rep[Option[String]] = column[Option[String]]("text", O.Length(2000,varying=true), O.Default(None))

    /** Foreign key referencing Users (database name items_user_id_fkey) */
    lazy val usersFk = foreignKey("items_user_id_fkey", userId, Users)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Items */
  lazy val Items = new TableQuery(tag => new Items(tag))

  /** Entity class storing rows of table Jobs
   *  @param salary Database column salary SqlType(varchar), Length(50,true), Default(None)
   *  @param location Database column location SqlType(varchar), Length(20,true), Default(None)
   *  @param remote Database column remote SqlType(varchar), Length(20,true), Default(None)
   *  @param hours Database column hours SqlType(varchar), Length(100,true), Default(None)
   *  @param cId Database column c_id SqlType(int4), Default(None)
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(50,true)
   *  @param description Database column description SqlType(varchar), Length(5000,true), Default(None)
   *  @param q1 Database column q1 SqlType(varchar), Length(1000,true), Default(None)
   *  @param q2 Database column q2 SqlType(varchar), Length(1000,true), Default(None)
   *  @param q3 Database column q3 SqlType(varchar), Length(1000,true), Default(None) */
  case class JobsRow(salary: Option[String] = None, location: Option[String] = None, remote: Option[String] = None, hours: Option[String] = None, cId: Option[Int] = None, id: Int, name: String, description: Option[String] = None, q1: Option[String] = None, q2: Option[String] = None, q3: Option[String] = None)
  /** GetResult implicit for fetching JobsRow objects using plain SQL queries */
  implicit def GetResultJobsRow(implicit e0: GR[Option[String]], e1: GR[Option[Int]], e2: GR[Int], e3: GR[String]): GR[JobsRow] = GR{
    prs => import prs._
    JobsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<[Int], <<[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table jobs. Objects of this class serve as prototypes for rows in queries. */
  class Jobs(_tableTag: Tag) extends profile.api.Table[JobsRow](_tableTag, "jobs") {
    def * = (salary, location, remote, hours, cId, id, name, description, q1, q2, q3).<>(JobsRow.tupled, JobsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((salary, location, remote, hours, cId, Rep.Some(id), Rep.Some(name), description, q1, q2, q3)).shaped.<>({r=>import r._; _6.map(_=> JobsRow.tupled((_1, _2, _3, _4, _5, _6.get, _7.get, _8, _9, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column salary SqlType(varchar), Length(50,true), Default(None) */
    val salary: Rep[Option[String]] = column[Option[String]]("salary", O.Length(50,varying=true), O.Default(None))
    /** Database column location SqlType(varchar), Length(20,true), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(20,varying=true), O.Default(None))
    /** Database column remote SqlType(varchar), Length(20,true), Default(None) */
    val remote: Rep[Option[String]] = column[Option[String]]("remote", O.Length(20,varying=true), O.Default(None))
    /** Database column hours SqlType(varchar), Length(100,true), Default(None) */
    val hours: Rep[Option[String]] = column[Option[String]]("hours", O.Length(100,varying=true), O.Default(None))
    /** Database column c_id SqlType(int4), Default(None) */
    val cId: Rep[Option[Int]] = column[Option[Int]]("c_id", O.Default(None))
    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))
    /** Database column description SqlType(varchar), Length(5000,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(5000,varying=true), O.Default(None))
    /** Database column q1 SqlType(varchar), Length(1000,true), Default(None) */
    val q1: Rep[Option[String]] = column[Option[String]]("q1", O.Length(1000,varying=true), O.Default(None))
    /** Database column q2 SqlType(varchar), Length(1000,true), Default(None) */
    val q2: Rep[Option[String]] = column[Option[String]]("q2", O.Length(1000,varying=true), O.Default(None))
    /** Database column q3 SqlType(varchar), Length(1000,true), Default(None) */
    val q3: Rep[Option[String]] = column[Option[String]]("q3", O.Length(1000,varying=true), O.Default(None))

    /** Foreign key referencing Company (database name jobs_c_id_fkey) */
    lazy val companyFk = foreignKey("jobs_c_id_fkey", cId, Company)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Jobs */
  lazy val Jobs = new TableQuery(tag => new Jobs(tag))

  /** Entity class storing rows of table Recruiters
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true)
   *  @param cId Database column c_id SqlType(int4), Default(None) */
  case class RecruitersRow(id: Int, username: String, password: String, cId: Option[Int] = None)
  /** GetResult implicit for fetching RecruitersRow objects using plain SQL queries */
  implicit def GetResultRecruitersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[RecruitersRow] = GR{
    prs => import prs._
    RecruitersRow.tupled((<<[Int], <<[String], <<[String], <<?[Int]))
  }
  /** Table description of table recruiters. Objects of this class serve as prototypes for rows in queries. */
  class Recruiters(_tableTag: Tag) extends profile.api.Table[RecruitersRow](_tableTag, "recruiters") {
    def * = (id, username, password, cId).<>(RecruitersRow.tupled, RecruitersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), cId)).shaped.<>({r=>import r._; _1.map(_=> RecruitersRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
    /** Database column c_id SqlType(int4), Default(None) */
    val cId: Rep[Option[Int]] = column[Option[Int]]("c_id", O.Default(None))

    /** Foreign key referencing Company (database name recruiters_c_id_fkey) */
    lazy val companyFk = foreignKey("recruiters_c_id_fkey", cId, Company)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Recruiters */
  lazy val Recruiters = new TableQuery(tag => new Recruiters(tag))

  /** Entity class storing rows of table RProfile
   *  @param description Database column description SqlType(varchar), Length(2000,true), Default(None)
   *  @param currentPosition Database column current_position SqlType(varchar), Length(200,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(100,true), Default(None)
   *  @param location Database column location SqlType(varchar), Length(100,true), Default(None)
   *  @param email Database column email SqlType(varchar), Length(100,true), Default(None)
   *  @param pronouns Database column pronouns SqlType(varchar), Length(100,true), Default(None)
   *  @param rId Database column r_id SqlType(int4), Default(None) */
  case class RProfileRow(description: Option[String] = None, currentPosition: Option[String] = None, name: Option[String] = None, location: Option[String] = None, email: Option[String] = None, pronouns: Option[String] = None, rId: Option[Int] = None)
  /** GetResult implicit for fetching RProfileRow objects using plain SQL queries */
  implicit def GetResultRProfileRow(implicit e0: GR[Option[String]], e1: GR[Option[Int]]): GR[RProfileRow] = GR{
    prs => import prs._
    RProfileRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int]))
  }
  /** Table description of table r_profile. Objects of this class serve as prototypes for rows in queries. */
  class RProfile(_tableTag: Tag) extends profile.api.Table[RProfileRow](_tableTag, "r_profile") {
    def * = (description, currentPosition, name, location, email, pronouns, rId).<>(RProfileRow.tupled, RProfileRow.unapply)

    /** Database column description SqlType(varchar), Length(2000,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(2000,varying=true), O.Default(None))
    /** Database column current_position SqlType(varchar), Length(200,true), Default(None) */
    val currentPosition: Rep[Option[String]] = column[Option[String]]("current_position", O.Length(200,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(100,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(100,varying=true), O.Default(None))
    /** Database column location SqlType(varchar), Length(100,true), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(100,varying=true), O.Default(None))
    /** Database column email SqlType(varchar), Length(100,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(100,varying=true), O.Default(None))
    /** Database column pronouns SqlType(varchar), Length(100,true), Default(None) */
    val pronouns: Rep[Option[String]] = column[Option[String]]("pronouns", O.Length(100,varying=true), O.Default(None))
    /** Database column r_id SqlType(int4), Default(None) */
    val rId: Rep[Option[Int]] = column[Option[Int]]("r_id", O.Default(None))

    /** Foreign key referencing Recruiters (database name r_profile_r_id_fkey) */
    lazy val recruitersFk = foreignKey("r_profile_r_id_fkey", rId, Recruiters)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table RProfile */
  lazy val RProfile = new TableQuery(tag => new RProfile(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true) */
  case class UsersRow(id: Int, username: String, password: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password).<>(UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}