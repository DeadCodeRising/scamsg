name := "scamsg"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-actors" % _)