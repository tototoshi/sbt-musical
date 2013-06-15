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
import Keys._
import complete.DefaultParsers._
import com.github.tototoshi.itunes.iTunes

object Plugin extends sbt.Plugin {
  private[this] val volumeParser = Space ~> (
    List("up", "down", "mute", "unmute") ++ (0 to 100).map(_.toString)
  ).map(token(_)).reduce(_ | _) | any.+.string

  lazy val play = Command.command("itunes-play") { (state) => iTunes.play(); state }
  lazy val pause = Command.command("itunes-pause") { (state) => iTunes.pause(); state }
  lazy val stop = Command.command("itunes-stop") { (state) => iTunes.stop(); state }
  lazy val next = Command.command("itunes-next") { (state) => iTunes.next(); state }
  lazy val prev = Command.command("itunes-prev") { (state) => iTunes.prev(); state }
  lazy val info = Command.command("itunes-info") { (state) => iTunes.prev(); state }
  lazy val vol = Command("itunes-vol")(_ => volumeParser) { (state, arg) => iTunes.vol(arg); state }

  lazy val hook = Command("♪", musicalBriefHelp, musicalDetailHelp)(BasicCommands.otherCommandParser) { (state, args) =>
    "itunes-play" :: args :: "itunes-pause" :: state
  }

  lazy val musicalBriefHelp = ("♪ <command>", "music trigger")
  lazy val musicalDetailHelp = "Play music while executing commands."

  override lazy val settings = Seq(
    commands ++= Seq(play, pause, stop, next, prev, info, vol, hook)
  )

}
