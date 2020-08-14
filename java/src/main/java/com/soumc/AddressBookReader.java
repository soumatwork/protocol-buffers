package com.soumc;

import com.soumc.models.AddressBookProtos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddressBookReader {
	private final String addressBookPath;

	public AddressBookReader(String addressBookPath) {
		this.addressBookPath = addressBookPath;
	}

	public int read() throws IOException {
		try (InputStream input = new FileInputStream(addressBookPath)) {
			AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook
					.parseFrom(input);
			return addressBook.getPeopleList().size();
		}
	}
}
