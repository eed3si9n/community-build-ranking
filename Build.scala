import sbt._,Keys._

object build extends Build{

  lazy val root = Project(
    "sbt-plugin-ranking",
    file(".")
  ).settings(
    scalaVersion := "2.9.2",
    licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))
  ).dependsOn(
    uri("git://github.com/xuwei-k/ghscala.git#1bded977f0ce29b")
  )

}
