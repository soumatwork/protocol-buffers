package com.soumc.io;

import java.io.IOException;

public interface Reader<T> {
  T read(Class<T> toType) throws IOException;
}
