package com.soumc;

import com.soumc.io.Reader;
import com.soumc.models.AddressBookProtos;

import java.io.IOException;

public class Deserializer {
  private final Reader<AddressBookProtos.AddressBook> reader;

  public Deserializer(Reader<AddressBookProtos.AddressBook> reader) {
    this.reader = reader;
  }

  public int deserialize() throws IOException {
    return reader.read(AddressBookProtos.AddressBook.class).getPeopleList().size();
  }
}
