package com.soumc;

import com.soumc.io.Reader;
import com.soumc.models.AddressBookProtos;

import java.io.IOException;

public class AddressBookReader {
  private final Reader<AddressBookProtos.AddressBook> reader;

  public AddressBookReader(Reader<AddressBookProtos.AddressBook> reader) {
    this.reader = reader;
  }

  public int read() throws IOException {
    return reader.read(AddressBookProtos.AddressBook.class).getPeopleList().size();
  }
}
