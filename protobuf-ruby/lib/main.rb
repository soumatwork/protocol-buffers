# frozen_string_literal: true

require '../lib/models/address_book_creator'
require '../lib/models/addressbook_pb'
require 'json'

# Main
class Main
  class << self

    NUMBER_OF_PERSON = 1_000_000
    def process
      address_book = AddressBookCreator.address_book(NUMBER_OF_PERSON)
      p 'protobuf'
      write(address_book)
      read

      p 'json'
      write_json(address_book)
      read_json
    end

    private

    def write(address_book)
      starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
      File.open('../addressbook.data', 'wb') do |f|
        f.write(Proto::AddressBook.encode(address_book))
      end
      p "Time taken to write: #{Process.clock_gettime(Process::CLOCK_MONOTONIC) - starting} seconds"
    end

    def read
      starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
      File.open('../addressbook.data', 'rb') do |f|
        p Proto::AddressBook.decode(f.read).people.count == NUMBER_OF_PERSON
      end
      p "Time taken to read: #{Process.clock_gettime(Process::CLOCK_MONOTONIC) - starting} seconds"
    end

    def write_json(address_book)
      starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
      File.open('../addressbook.json', 'wb') do |f|
        f.write(JSON.generate(address_book.to_h))
      end
      p "Time taken to write: #{Process.clock_gettime(Process::CLOCK_MONOTONIC) - starting} seconds"
    end

    def read_json
      starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
      File.open('../addressbook.json', 'rb') do |f|
        p AddressBookCreator.address_book_from_hash(JSON.parse(f.read, symbolize_names: true)).people.count == NUMBER_OF_PERSON
      end
      p "Time taken to read: #{Process.clock_gettime(Process::CLOCK_MONOTONIC) - starting} seconds"
    end
  end
end

Main.process
