/**
 *
 * Copyright 2013 Toshiyuki Takahashi
 *
 * This work is licensed under a
 * Creative Commons Attribution-Share Alike 2.0 UK: England & Wales License
 * http://creativecommons.org/licenses/by-sa/2.0/uk/
 * In human-readble terms: you're free to copy, distribute and modify
 * providing you maintain attribution and licence.
 *
 * Use at your own risk, no fitness for purpose implied, etc, etc.
 *
 */
package com.github.tototoshi.sbt.musical

import sbt._
import sys.process.Process
import Keys._

object SbtMusicalPlugin extends AutoPlugin {

  override def requires = sbt.plugins.CorePlugin
  override def trigger = allRequirements

  private[this] lazy val musicalBriefHelp = ("♪ <command>","Trigger to play YouTube video")
  private[this] lazy val musicalDetailHelp = "Play a video while executing commands."

  object autoImport {
    val sbtMusicalYouTubePlayer = settingKey[String]("Command name")
    val sbtMusicalYouTubeVideoUrl = settingKey[String]("Video URL")
    val sbtMusicalHook = settingKey[Command]("Trigger to play a video")
  }

  import autoImport._

  private lazy val settings: Seq[Setting[_]] = Seq(
    sbtMusicalYouTubePlayer := "sbt-musical-youtube-player",
    sbtMusicalYouTubeVideoUrl := "https://www.youtube.com/watch?v=b-Cr0EWwaTk&t=15",
    sbtMusicalHook := Def.setting {
      Command("♪", musicalBriefHelp, musicalDetailHelp)(BasicCommands.otherCommandParser) { (state, args) =>
        val p = Process(Seq(sbtMusicalYouTubePlayer.value, sbtMusicalYouTubeVideoUrl.value)).run()
        try {
          Command.process(args, state)
        } finally {
          p.destroy()
        }
      }
    }.value,
    commands += sbtMusicalHook.value
  )

  override lazy val buildSettings: Seq[Setting[_]] = settings
  override lazy val projectSettings: Seq[Setting[_]] = settings

}
