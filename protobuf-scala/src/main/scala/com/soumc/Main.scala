package com.soumc

import com.soumc.models.addressbook.Person.PhoneNumber
import com.soumc.models.addressbook.Person.PhoneType.MOBILE
import com.soumc.models.addressbook.{AddressBook, Person}

import cats.effect._
import cats.implicits._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    IO.pure(addressBook).flatMap(a => IO(a.writeTo(System.out))).as(ExitCode.Success)
  }

  private def addressBook = {
    AddressBook(people = List(Person(
      id = 1, name = "full name", email = "a@a.com",
      phones = List(
        PhoneNumber(number = "0000000000", `type` = MOBILE)
      )
    )))
  }
}
