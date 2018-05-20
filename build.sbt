name := "scala-typesafe"

organization := "com.alexey"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.6"

scalaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "test"

libraryDependencies ++= Seq(
  "ru.maxkar" %% "lib-json" % "0.0.4",

  "org.scalatest" %% "scalatest" % "3.0.0"
)
