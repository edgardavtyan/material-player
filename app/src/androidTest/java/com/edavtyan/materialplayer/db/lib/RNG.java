package com.edavtyan.materialplayer.db.lib;

import java.util.Random;

public final class RNG {
	private static final char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	private static final Random random = new Random();

	public static String string() {
		int length = 10;
		char[] buffer = new char[length];
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(chars.length);
			char c = chars[index];
			buffer[i] = c;
		}

		return new String(buffer);
	}

	public static int number() {
		return random.nextInt();
	}
}
