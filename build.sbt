name := """playfluffy1"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.webjars" % "webjars-play_2.11" % "2.5.0-2",
  "org.webjars" % "jquery" % "2.2.4",
  "org.webjars" % "bootstrap" % "3.3.6",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.1.8"
)

javaOptions in Test += "-Dconfig.file=conf/application.test.conf"

flywayUrl := "jdbc:mariadb://127.0.0.1:3306/playfluffy1"
flywayUser := "root"
flywayPassword := "root"
flywayLocations := Seq("filesystem:conf/flyway")
