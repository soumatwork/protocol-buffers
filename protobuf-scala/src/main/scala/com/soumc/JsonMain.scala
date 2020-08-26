package com.soumc

import java.io._

import cats.effect._
import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.auto._
import com.soumc.models.addressbook.Person.PhoneNumber
import com.soumc.models.addressbook.Person.PhoneType.MOBILE
import com.soumc.models.addressbook.{AddressBook, Person}

import scala.io.Source
import scala.util.Try

object JsonMain extends IOApp {
  val NUMBER_OF_PEOPLE = 1000

  def run(args: List[String]): IO[ExitCode] = {
    for {
      a <- IO.pure(addressBook)
      writeStart <- IO(System.nanoTime)
      _ <- write(a)
      currentTime <- IO(System.nanoTime)
      writeDuration = (currentTime - writeStart) / 1e9d
      _ <- IO(println(s"Time taken to write: $writeDuration"))
      readStart <- IO(System.nanoTime)
      readAddress <- read
      currentReadTime <- IO(System.nanoTime)
      readDuration = (currentReadTime - readStart) / 1e9d
      _ <- IO(println(s"Time taken to read: $readDuration"))
      s = if (readAddress.people.size == NUMBER_OF_PEOPLE) ExitCode.Success else ExitCode.Error
    } yield s
  }

  private def addressBook = {
    val people = (1 to NUMBER_OF_PEOPLE).map(i => Person(
      id = i, name = "full name", email = "a@a.com",
      phones = List(
        PhoneNumber(number = "0000000000", `type` = MOBILE)
      )
    ))
    AddressBook(people = people)
  }

  private def read: IO[AddressBook] = {
    IO(new File("addressbook.json"))
      .flatMap(f => readFile(f))
      .flatMap(str => IO.fromEither(decode[AddressBook](str)))
  }

  private def write(addressBook: AddressBook): IO[Unit] = {
    IO(new File("addressbook.json"))
      .flatMap(f => outputStream(f).use(out => IO(out.print(addressBook.asJson.noSpaces))))
  }


  def readFile(f: File): IO[String] = {
    IO.fromTry {
      Try {
        val source = Source.fromFile(f)
        try source.mkString finally source.close()
      }
    }
  }

  def outputStream(f: File): Resource[IO, PrintWriter] =
    Resource.make {
      IO(new PrintWriter(f))
    } { outStream =>
      IO(outStream.close()).handleErrorWith(_ => IO.unit)
    }
}
