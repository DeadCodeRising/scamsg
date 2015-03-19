name := "scamsg"

version := "1.0"

scalaVersion := "2.10.4"

EclipseKeys.withSource := true

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies += 
	"com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"