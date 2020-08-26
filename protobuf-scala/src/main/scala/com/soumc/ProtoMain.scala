package com.soumc

import java.io._

import cats.effect._
import com.soumc.models.addressbook.Person.PhoneNumber
import com.soumc.models.addressbook.Person.PhoneType.MOBILE
import com.soumc.models.addressbook.{AddressBook, Person}

object ProtoMain extends IOApp {
  val NUMBER_OF_PEOPLE = 50000

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
    IO(new File("addressbook.data"))
      .flatMap(f => inputStream(f).use(in => IO(AddressBook.parseFrom(in))))
  }

  private def write(addressBook: AddressBook): IO[Unit] = {
    IO(new File("addressbook.data"))
      .flatMap(f => outputStream(f).use(out => IO(addressBook.writeTo(out))))
  }


  def inputStream(f: File): Resource[IO, FileInputStream] =
    Resource.make {
      IO(new FileInputStream(f))
    } { inStream =>
      IO(inStream.close()).handleErrorWith(_ => IO.unit)
    }

  def outputStream(f: File): Resource[IO, FileOutputStream] =
    Resource.make {
      IO(new FileOutputStream(f))
    } { outStream =>
      IO(outStream.close()).handleErrorWith(_ => IO.unit)
    }
}
