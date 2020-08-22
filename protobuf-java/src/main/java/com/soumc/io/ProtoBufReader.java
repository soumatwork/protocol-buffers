package com.soumc.io;

import com.google.protobuf.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProtoBufReader<T> implements Reader<T> {
  private final String addressBookPath;
  private final Parser<T> parser;

  public ProtoBufReader(String addressBookPath, Parser<T> parser) {
    this.addressBookPath = addressBookPath;
    this.parser = parser;
  }

  @Override
  public T read(Class<T> toType) throws IOException {
    try (InputStream input = new FileInputStream(addressBookPath)) {
      return parser.parseFrom(input);
    }
  }
}
