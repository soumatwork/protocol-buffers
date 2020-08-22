package com.soumc;

import com.soumc.io.Writer;
import com.soumc.models.AddressBookProtos;
import com.soumc.models.PersonCreator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Serializer {
  private final PersonCreator personCreator;
  private final Writer<AddressBookProtos.AddressBook> writer;

  public Serializer(Writer<AddressBookProtos.AddressBook> writer) {
    this.writer = writer;
    this.personCreator = new PersonCreator();
  }

  public void serialize(int numberOfPerson) throws IOException {
    save(IntStream.rangeClosed(1, numberOfPerson).mapToObj(personCreator::person)
        .collect(Collectors.toList()));
  }

  private void save(List<AddressBookProtos.Person> persons) throws IOException {
    AddressBookProtos.AddressBook.Builder addressBook = AddressBookProtos.AddressBook
        .newBuilder();
    addressBook.addAllPeople(persons);
    writer.write(addressBook.build());
  }
}
