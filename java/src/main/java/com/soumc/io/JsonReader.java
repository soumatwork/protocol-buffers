package com.soumc.io;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader<T> implements Reader<T> {
  private final String addressBookPath;

  public JsonReader(String addressBookPath) {
    this.addressBookPath = addressBookPath;
  }

  @Override
  public T read(Class<T> toType) throws IOException {
    Gson g = new Gson();
    try (FileReader input = new FileReader(addressBookPath);
         BufferedReader bufferedReader = new BufferedReader(input)) {
      return g.fromJson(bufferedReader, toType);
    }
  }
}
