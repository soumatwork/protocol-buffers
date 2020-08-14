package com.soumc;

import com.soumc.io.ProtoBufReader;
import com.soumc.io.ProtoBufWriter;
import com.soumc.io.Reader;
import com.soumc.io.Writer;
import com.soumc.models.AddressBookProtos;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws Exception {
    write(args[0]);
    read(args[0]);
  }

  private static void write(String path) throws IOException {
    long start = System.currentTimeMillis();
    Writer<AddressBookProtos.AddressBook> writer = new ProtoBufWriter<>(path);
    new AddressBookWriter(writer).write(10);
    System.out.println("Time taken to write: " + (System.currentTimeMillis() - start));
  }

  private static void read(String path) throws IOException {
    long start = System.currentTimeMillis();
    Reader<AddressBookProtos.AddressBook> reader
        = new ProtoBufReader<>(path, AddressBookProtos.AddressBook.parser());
    new AddressBookReader(reader).read();
    System.out.println("Time taken to read: " + (System.currentTimeMillis() - start));
  }
}
