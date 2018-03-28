package com.github.sguzman.scala.go.massage

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.Base64

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length != 1 && args.length != 2) {
      return
    }

    val lineLength = if (args.length == 2) args(1).toInt
    else 10000

    val fileName = args.head
    if (!new File(fileName).exists) {
      return
    }

    val file = Files.readAllBytes(Paths.get(fileName))
    val encoded = Base64.getEncoder.encode(file)

    val string = "const shows = " ++ encoded.grouped(lineLength).map(a => "\"" ++ a.map(_.toChar).mkString ++  "\"").mkString(" + ") ++ ";"
    println(string)
  }
}
