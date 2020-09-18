name := "kraftwerk-scala"

lazy val scala213 = "2.13.3"
lazy val scala212 = "2.12.10"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala213, scala212, scala211)

ThisBuild / organization := "dev.marksman"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala211

crossScalaVersions := supportedScalaVersions

libraryDependencies += "dev.marksman" % "kraftwerk" % "0.9.1"

scalacOptions ++= Seq("-target:jvm-1.8")
