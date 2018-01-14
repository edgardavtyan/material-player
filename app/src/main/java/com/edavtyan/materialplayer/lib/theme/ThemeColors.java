package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;

import com.edavtyan.materialplayer.R;

import lombok.Getter;

public class ThemeColors {
	private static SparseIntArray COLORS;

	private final @Getter int colorPrimary;
	private final @Getter int colorPrimaryDark;

	public ThemeColors(Context context, int color) {
		if (COLORS == null) {
			COLORS = new SparseIntArray();
			COLORS.append(ContextCompat.getColor(context, R.color.red), R.array.color_red);
			COLORS.append(ContextCompat.getColor(context, R.color.pink), R.array.color_pink);
			COLORS.append(ContextCompat.getColor(context, R.color.purple), R.array.color_purple);
			COLORS.append(ContextCompat.getColor(context, R.color.deepPurple), R.array.color_deepPurple);
			COLORS.append(ContextCompat.getColor(context, R.color.indigo), R.array.color_indigo);
			COLORS.append(ContextCompat.getColor(context, R.color.blue), R.array.color_blue);
			COLORS.append(ContextCompat.getColor(context, R.color.lightBlue), R.array.color_lightBlue);
			COLORS.append(ContextCompat.getColor(context, R.color.cyan), R.array.color_cyan);
			COLORS.append(ContextCompat.getColor(context, R.color.teal), R.array.color_teal);
			COLORS.append(ContextCompat.getColor(context, R.color.green), R.array.color_green);
			COLORS.append(ContextCompat.getColor(context, R.color.lightGreen), R.array.color_lightGreen);
			COLORS.append(ContextCompat.getColor(context, R.color.lime), R.array.color_lime);
			COLORS.append(ContextCompat.getColor(context, R.color.yellow), R.array.color_yellow);
			COLORS.append(ContextCompat.getColor(context, R.color.amber), R.array.color_amber);
			COLORS.append(ContextCompat.getColor(context, R.color.orange), R.array.color_orange);
			COLORS.append(ContextCompat.getColor(context, R.color.deepOrange), R.array.color_deepOrange);
			COLORS.append(ContextCompat.getColor(context, R.color.brown), R.array.color_brown);
			COLORS.append(ContextCompat.getColor(context, R.color.grey), R.array.color_grey);
			COLORS.append(ContextCompat.getColor(context, R.color.blueGrey), R.array.color_blueGrey);
		}

		int colorsId = COLORS.get(color);
		int[] colorsArray = context.getResources().getIntArray(colorsId);
		colorPrimary = colorsArray[0];
		colorPrimaryDark = colorsArray[1];
	}
}
