package com.soumc.io;

import java.io.IOException;

public interface Writer<T> {
	void write(T t) throws IOException;
}
