# sbt-musical

NO MUSIC, NO BUILD. Enjoy your compile time.

## Requirement

 - Mac & iTunes
 - bash-itunes

## Install

 1. Buy a Mac. (Sorry...)
 2. Install bash-itunes

```
$ mkdir ~/bin  # if not exists
$ https://raw.githubusercontent.com/illusori/bash-itunes/master/itunes > ~/bin/itunes && chmod 0755 !#:3
```

 3. In your ~/.sbt/plugins/build.sbt

```scala
addSbtPlugin("com.github.tototoshi" % "sbt-musical" % "0.1.2")
```

## Usage

First, open iTunes.

Prefix arbitrary commands with ♪. This will play music while executing the command.

```
> ♪ compile
```

```
> ♪ test
```


## License

Creative Commons Attribution-Share Alike 2.0 UK: England &amp; Wales License

http://creativecommons.org/licenses/by-sa/2.0/uk/
