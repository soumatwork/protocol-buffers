# frozen_string_literal: true

require '../lib/models/address_book_creator'
require '../lib/models/addressbook_pb'
require 'json'

# Main
class Main
  class << self

    NUMBER_OF_PERSON = 50_000
    def process
      address_book = AddressBookCreator.address_book(NUMBER_OF_PERSON)
      write(address_book)
      read

      write_json(address_book)
      read_json
    end

    private

    def write(address_book)
      File.open('../addressbook.data', 'wb') do |f|
        f.write(address_book.to_proto)
      end
    end

    def read
      File.open('../addressbook.data', 'rb') do |f|
        p Proto::AddressBook.decode(f.read).people.count == NUMBER_OF_PERSON
      end
    end

    def write_json(address_book)
      File.open('../addressbook.json', 'wb') do |f|
        f.write(Proto::AddressBook.encode_json(address_book))
      end
    end

    def read_json
      File.open('../addressbook.json', 'rb') do |f|
        p Proto::AddressBook.decode_json(f.read).people.count == NUMBER_OF_PERSON
      end
    end
  end
end

Main.process
