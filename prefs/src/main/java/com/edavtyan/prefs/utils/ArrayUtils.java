package com.edavtyan.prefs.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	public static List<String> asStringList(CharSequence[] array) {
		List<String> list = new ArrayList<>();
		for (CharSequence charSequence : array) {
			list.add(charSequence.toString());
		}

		return list;
	}

	public static List<Integer> asIntList(int[] array) {
		List<Integer> list = new ArrayList<>(array.length);
		for (int element : array) {
			list.add(element);
		}

		return list;
	}
}
