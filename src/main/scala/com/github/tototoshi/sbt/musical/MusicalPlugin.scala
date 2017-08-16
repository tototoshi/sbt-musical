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

object MusicalPlugin extends sbt.AutoPlugin {
  private[this] val volumeParser = Space ~> (
    List("up", "down", "mute", "unmute") ++ (0 to 100).map(_.toString)
  ).map(token(_)).reduce(_ | _) | any.+.string

  private[this] lazy val play = Command.command("itunes-play") { (state) => iTunes.play(); state }
  private[this] lazy val pause = Command.command("itunes-pause") { (state) => iTunes.pause(); state }
  private[this] lazy val stop = Command.command("itunes-stop") { (state) => iTunes.stop(); state }
  private[this] lazy val next = Command.command("itunes-next") { (state) => iTunes.next(); state }
  private[this] lazy val prev = Command.command("itunes-prev") { (state) => iTunes.prev(); state }
  private[this] lazy val info = Command.command("itunes-info") { (state) => iTunes.prev(); state }
  private[this] lazy val vol = Command("itunes-vol")(_ => volumeParser) { (state, arg) => iTunes.vol(arg); state }

  private[this] lazy val hook = Command("♪", musicalBriefHelp, musicalDetailHelp)(BasicCommands.otherCommandParser) { (state, args) =>
    try {
      Command.process("itunes-play", state)
      Command.process(args, state)
    } finally {
      Command.process("itunes-pause", state)
    }
  }

  private[this] lazy val musicalBriefHelp = ("♪ <command>", "music trigger")
  private[this] lazy val musicalDetailHelp = "Play music while executing commands."

  override lazy val projectSettings = Seq(
    commands ++= Seq(play, pause, stop, next, prev, info, vol, hook)
  )

}
