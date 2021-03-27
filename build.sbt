import sbt._
import sbt.Keys._

lazy val sbtmusical = project.in(file("."))
  .settings(
    name := "sbt-musical",
    organization := "com.github.tototoshi",
    version := "1.0.0-SNAPSHOT",
    sbtPlugin := true,
    libraryDependencies ++= Seq(),
    publishMavenStyle := true,
    publishTo := Some(_publishTo(version.value)),
    publishArtifact in Test := false,
    pomExtra := _pomExtra
  )


def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) "snapshots" at nexus + "content/repositories/snapshots"
  else "releases" at nexus + "service/local/staging/deploy/maven2"
}

val _pomExtra =
  <url>http://github.com/tototoshi/sbt-musical</url>
    <licenses>
      <license>
        <name>Creative Commons Attribution-Share Alike 2.0 UK: England &amp; Wales License</name>
        <url>http://creativecommons.org/licenses/by-sa/2.0/uk/</url>
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
        <url>http://tototoshi.github.com</url>
      </developer>
    </developers>
