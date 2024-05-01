package models

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile", 
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/tigerhire?user=tigerhire&password=plz.hire!ME",
    "/users/tjarrett/jobsiteProject/server/app", 
    "models", None, None, true, false
  )
}
