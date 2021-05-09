import sbt._
import sbt.Keys._

lazy val sbtmusical = project.in(file("."))
  .settings(
    name := "sbt-musical",
    organization := "com.github.tototoshi",
    version := "1.0.0",
    sbtPlugin := true,
    libraryDependencies ++= Seq(),
    publishMavenStyle := true,
    publishTo := Some(_publishTo(version.value)),
    Test / publishArtifact := false,
    pomExtra := _pomExtra
  )


def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) "snapshots" at nexus + "content/repositories/snapshots"
  else "releases" at nexus + "service/local/staging/deploy/maven2"
}

val _pomExtra =
    <url>https://github.com/tototoshi/sbt-musical</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>https://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:tototoshi/scala-musical</url>
      <connection>scm:git:git@github.com:tototoshi/sbt-musical.git</connection>
    </scm>
    <developers>
      <developer>
        <id>tototoshi</id>
        <name>Toshiyuki Takahashi</name>
        <url>https://tototoshi.github.io</url>
      </developer>
    </developers>
