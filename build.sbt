name := """play-site1"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.webjars" % "webjars-play_2.11" % "2.5.0-2",
  "org.webjars" % "jquery" % "2.2.4",
  "org.webjars" % "bootstrap" % "3.3.6"
)
