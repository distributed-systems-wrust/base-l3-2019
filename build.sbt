lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "pl.edu.wroc.pwr",
  scalaVersion := "2.12.8",
  test in assembly := {},
  libraryDependencies += "log4j" % "log4j" % "1.2.17"
)

lazy val proto = (project in file("proto")).enablePlugins(AkkaGrpcPlugin)

lazy val client = (project in file("client")).dependsOn(proto).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("com.example.helloworld.GreeterClient"),
  )

lazy val server = (project in file("server")).dependsOn(proto).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("com.example.helloworld.GreeterServer"),
  )
