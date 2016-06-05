package com.edavtyan.materialplayer.app.utils;

public final class ArrayUtils {
	private ArrayUtils() {}

	public static short[] toShort(int[] ints) {
		short[] shorts = new short[ints.length];
		for (int i = 0; i < ints.length; i++) {
			shorts[i] = (short) ints[i];
		}
		return shorts;
	}
}
