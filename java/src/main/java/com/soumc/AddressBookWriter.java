package com.soumc;

import com.soumc.models.AddressBookProtos;
import com.soumc.models.PersonCreator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddressBookWriter {
	private final PersonCreator creator;
	private final String addressBookPath;

	public AddressBookWriter(String addressBookPath) {
		this.creator = new PersonCreator();
		this.addressBookPath = addressBookPath;
	}

	public void write(int numberOfPerson) throws IOException {
		save(IntStream.rangeClosed(1, numberOfPerson).mapToObj(creator::person)
				.collect(Collectors.toList()));
	}

	private void save(List<AddressBookProtos.Person> persons) throws IOException {
		AddressBookProtos.AddressBook.Builder addressBook = AddressBookProtos.AddressBook
				.newBuilder();
		addressBook.addAllPeople(persons);

		try (FileOutputStream output = new FileOutputStream(addressBookPath)) {
			addressBook.build().writeTo(output);
		}
	}
}
