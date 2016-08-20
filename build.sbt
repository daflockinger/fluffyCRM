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
  "org.webjars" % "momentjs" % "2.14.1",
  "org.webjars" % "Eonasdan-bootstrap-datetimepicker" % "4.17.37-1",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.1.8"
)

javaOptions in Test += "-Dconfig.file=conf/application.test.conf"

flywayUrl := "jdbc:mariadb://127.0.0.1:3306/playfluffy1"
flywayUser := "root"
flywayPassword := "root"
flywayLocations := Seq("filesystem:conf/flyway")

EclipseKeys.preTasks := Seq(compile in Compile)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  