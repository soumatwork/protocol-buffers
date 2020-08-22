# frozen_string_literal: true

# Address book creator
class AddressBookCreator
  class << self
    def address_book(number_of_person)
      Proto::AddressBook.new(
        people: (1..number_of_person).map { |i| person(i) }
      )
    end

    private

    def person(id)
      Proto::Person.new(
        id: id,
        name: 'full name',
        email: 'abc@abc.com',
        phones: phones
      )
    end

    def phones
      [
        Proto::Person::PhoneNumber.new(
          number: '0000000000',
          type: Proto::Person::PhoneType::MOBILE
        )
      ]
    end
  end
end
