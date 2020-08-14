package com.soumc.io;

import com.google.protobuf.AbstractMessageLite;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProtoBufWriter<T extends AbstractMessageLite<?, ?>> implements Writer<T> {
  private final String addressBookPath;

  public ProtoBufWriter(String addressBookPath) {
    this.addressBookPath = addressBookPath;
  }

  @Override
  public void write(T t) throws IOException {
    try (FileOutputStream output = new FileOutputStream(addressBookPath)) {
      t.writeTo(output);
    }
  }
}

