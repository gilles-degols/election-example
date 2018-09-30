import play.sbt.PlayScala
import sbt.RootProject

name := "election-example"
 
version := "0.0.1"

scriptClasspath in bashScriptDefines ~= (cp => "/../../../../../../../../../../etc/net.degols/local:/../../../../../../../../../../usr/lib/net.degols/election/conf/application.conf" +: cp)

lazy val `filesgate` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.1"
lazy val playVersion = "2.6.1"
lazy val akkaVersion = "2.5.2"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

// Election library
val electionLibraryVersion = "0.0.1"
val electionPath = "../election"
lazy val electionLibrary: RootProject = RootProject(file(electionPath))
val useLocalElectionLibrary = true
val localElectionAvailable = scala.reflect.io.File(scala.reflect.io.Path(electionPath)).exists
lazy val proj = if(localElectionAvailable && useLocalElectionLibrary) {
  (project in file(".")).enablePlugins(PlayScala).dependsOn(electionLibrary)
} else {
  (project in file(".")).enablePlugins(PlayScala)
}

lazy val electionDependency = if(localElectionAvailable && useLocalElectionLibrary) {
  Seq()
} else {
  Seq("net.degols" %% "election" % electionLibraryVersion exclude("log4j", "log4j") exclude("org.slf4j","slf4j-log4j12"))
}
libraryDependencies ++= electionDependency

// Akka Remoting
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % akkaVersion