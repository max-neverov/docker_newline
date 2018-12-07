import dependencies._

lazy val commonSettings = Seq(
  name := "docker_newline",
  scalaVersion := "2.12.7",
  // do not include version in top level directory inside zip to cope with filenames in Dockerfile
  topLevelDirectory := Some(name.value)
)

lazy val sample = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      akkaHttp,
      akkaStreams,
      akkaHttpSprayJson,
    )
  )
