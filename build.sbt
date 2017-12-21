lazy val root = (project in file("."))
  .settings(
    name := "community-build-ranking",
    scalaVersion := "2.12.4",
    libraryDependencies ++= List(
      "com.eed3si9n" %% "gigahorse-github" % "gigahorse0.3.1_0.2.0",
      "com.eed3si9n" %% "gigahorse-okhttp" % "0.3.1")
  )
