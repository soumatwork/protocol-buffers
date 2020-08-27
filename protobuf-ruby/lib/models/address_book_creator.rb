# frozen_string_literal: true

# Address book creator
class AddressBookCreator
  class << self
    def address_book(number_of_person)
      Proto::AddressBook.new(
        people: (1..number_of_person).map { |i| person(i) }
      )
    end

    def address_book_from_hash(address_h)

      people = address_h[:people].map do |person|
        phones_array = person[:phones].map do |phone|
          Proto::Person::PhoneNumber.new(
            number: phone[:number],
            type: phone[:type]
          )
        end

        Proto::Person.new(
          id: person[:id],
          name: person[:name],
          email: person[:email],
          phones: phones_array
        )
      end

      Proto::AddressBook.new(
        people: people
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
