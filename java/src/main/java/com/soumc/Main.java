package com.soumc;

import com.soumc.io.*;
import com.soumc.models.AddressBookProtos;

import java.io.IOException;

public class Main {
  static private final int PERSONS = 1000000;

  public static void main(String[] args) throws Exception {
    System.out.println("protobuf");
    write(new ProtoBufWriter<>(args[0]));
    read(new ProtoBufReader<>(args[0], AddressBookProtos.AddressBook.parser()));

    System.out.println("json");
    write(new JsonWriter<>(args[1]));
    read(new JsonReader<>(args[1]));
  }

  private static void write(Writer<AddressBookProtos.AddressBook> writer) throws IOException {
    long start = System.currentTimeMillis();
    new AddressBookWriter(writer).write(PERSONS);
    System.out.println("Time taken to write: " + (System.currentTimeMillis() - start));
  }

  private static void read(Reader<AddressBookProtos.AddressBook> reader) throws IOException {
    long start = System.currentTimeMillis();
    System.out.println(new AddressBookReader(reader).read() == PERSONS);
    System.out.println("Time taken to read: " + (System.currentTimeMillis() - start));
  }
}
