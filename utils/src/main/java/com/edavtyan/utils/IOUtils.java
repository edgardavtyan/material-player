package com.edavtyan.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtils {
	public static void closeStream(InputStream inputStream) {
		closeCloseable(inputStream);
	}

	public static void closeStream(OutputStream outputStream) {
		closeCloseable(outputStream);
	}

	private static void closeCloseable(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
