package com.edavtyan.materialplayer.utils;

public class Strings {
	public static String join(int[] array, String separator) {
		String joinedArray = "";
		for (int anArray : array) joinedArray += anArray + separator;
		joinedArray = joinedArray.substring(0, joinedArray.length() - 1);
		return joinedArray;
	}

	public static boolean nullOrEmpty(String str) {
		return str == null || str.equals("");
	}
}
