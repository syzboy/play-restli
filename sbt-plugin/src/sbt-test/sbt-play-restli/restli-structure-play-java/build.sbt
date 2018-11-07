val pegasusVersion = "24.0.2"

lazy val api = (project in file("api"))
  .enablePlugins(RestliSchemaPlugin)
  .settings(
    name := "api",
    scalaVersion := "2.12.7",
    libraryDependencies ++= Seq(
      "com.linkedin.pegasus" % "data" % pegasusVersion,
      "com.google.code.findbugs" % "jsr305" % "3.0.0"
    )
  )

// Uses the complete PlayJava plugin; disables PlayLayoutPlugin in order to use the standard Restli project structure.
lazy val server = (project in file("server"))
  .enablePlugins(PlayRestliPlugin, PlayJava)
  .disablePlugins(PlayLayoutPlugin)
  .dependsOn(api)
  .settings(
    name := "server",
    scalaVersion := "2.12.7",
    restliModelApi := api,
    restliModelResourcePackages := Seq(
      "com.example.fortune.impl"
    ),
    libraryDependencies ++= Seq(
      "com.linkedin.pegasus" % "restli-server" % pegasusVersion,
      guice, akkaHttpServer, logback
    )
  )
