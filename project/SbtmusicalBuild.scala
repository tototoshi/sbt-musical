import sbt._
import sbt.Keys._

object SbtmusicalBuild extends Build {

  lazy val sbtmusical = Project(
    id = "sbt-musical",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "sbt-musical",
      organization := "com.github.tototoshi",
      version := "0.1.2",
      sbtPlugin := true,
      libraryDependencies ++= Seq(
        "com.github.tototoshi" %% "scala-itunes" % "0.1.0"
      )
    ) ++ publishingSettings
  )

  val publishingSettings = Seq(
    publishMavenStyle := true,
    publishTo <<= version { (v: String) => _publishTo(v) },
    publishArtifact in Test := false,
    pomExtra := _pomExtra
  )

  def _publishTo(v: String) = {
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases" at nexus + "service/local/staging/deploy/maven2")
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

}
