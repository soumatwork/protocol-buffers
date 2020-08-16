package com.soumc.models;

public class AddressBookCreator {

	public AddressBookProtos.Person person(int id) {
		AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder();
		person.setId(id);
		person.setName("full name");
		person.setEmail("a@a.com");
		AddressBookProtos.Person.PhoneNumber.Builder phone = AddressBookProtos.Person.PhoneNumber
				.newBuilder().setNumber("0000000000");
		phone.setType(AddressBookProtos.Person.PhoneType.MOBILE);
		person.addPhones(phone.build());
		return person.build();
	}
}
