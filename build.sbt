name := "scala-typesafe"

organization := "com.alexey"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.6"

val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "org.scalatest" %% "scalatest" % "3.0.0"
)
