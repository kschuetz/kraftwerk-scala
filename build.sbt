name := "kraftwerk-scala"

lazy val scala3 = "3.2.1"
lazy val scala213 = "2.13.10"
lazy val scala212 = "2.12.17"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala3, scala213, scala212, scala211)

ThisBuild / organization := "software.kes"
ThisBuild / version := "0.2.0-SNAPSHOT"
ThisBuild / scalaVersion := scala211

homepage := Some(url("https://github.com/kschuetz/kraftwerk-scala"))
scmInfo := Some(ScmInfo(url("https://github.com/kschuetz/kraftwerk-scala"),
  "git@github.com:kschuetz/kraftwerk-scala.git"))
developers := List(Developer("kschuetz",
  "Kevin Schuetz",
  "schuetzk@gmail.com",
  url("https://github.com/kschuetz")))
licenses += ("The MIT License (MIT)", url("http://choosealicense.com/licenses/mit"))
publishMavenStyle := true

crossScalaVersions := supportedScalaVersions

libraryDependencies += "software.kes" % "kraftwerk" % "0.11.0"

scalacOptions ++= Seq("-target:jvm-1.8")

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
