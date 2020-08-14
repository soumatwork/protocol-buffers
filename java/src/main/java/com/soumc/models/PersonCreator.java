package com.soumc.models;

import java.util.Random;

public class PersonCreator {
	private Random generator = new Random();

	public AddressBookProtos.Person person(int id) {
		AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder();
		person.setId(id);
		person.setName(randomName());
		person.setEmail(randomEmail());
		AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber
				.newBuilder().setNumber(randomPhoneNumber());
		phoneNumber.setType(AddressBookProtos.Person.PhoneType.MOBILE);
		person.addPhones(phoneNumber);
		return person.build();
	}

	private String randomPhoneNumber() {
		return "(" + generator.nextInt(7) + 1 + "" + generator.nextInt(8) + "" + generator
				.nextInt(8) + ")" + "-" + generator.nextInt(643) + 100 + "-" + generator
				.nextInt(8999) + 1000;
	}

	private String randomEmail() {
		return randomString().append("@abc.com").toString();
	}

	private String randomName() {
		return randomString().toString();
	}

	private StringBuilder randomString() {
		String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder builder = new StringBuilder();
		while (builder.length() < 10) {
			int index = (int) (generator.nextFloat() * SALT_CHARS.length());
			builder.append(SALT_CHARS.charAt(index));
		}
		return builder;
	}
}
