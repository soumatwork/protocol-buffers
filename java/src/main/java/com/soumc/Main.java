package com.soumc;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception {
		write(args[0]);
		read(args[0]);
	}

	private static void write(String path) throws IOException {
		long start = System.currentTimeMillis();
		new AddressBookWriter(path).write(1000000);
		System.out.println("Time taken to write: " + (System.currentTimeMillis() - start));
	}

	private static void read(String path) throws IOException {
		long start = System.currentTimeMillis();
		new AddressBookReader(path).read();
		System.out.println("Time taken to read: " + (System.currentTimeMillis() - start));
	}
}
