package com.soumc.io;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter<T> implements Writer<T> {
  private final String addressBookPath;

  public JsonWriter(String addressBookPath) {
    this.addressBookPath = addressBookPath;
  }

  @Override
  public void write(T t) throws IOException {
    Gson g = new Gson();
    try (FileWriter output = new FileWriter(addressBookPath);
         BufferedWriter bufferedWriter = new BufferedWriter(output)) {
      g.toJson(t, bufferedWriter);
    }
  }
}
