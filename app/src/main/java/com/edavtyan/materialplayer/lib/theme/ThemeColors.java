package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;

import com.edavtyan.materialplayer.R;

import lombok.Getter;

public class ThemeColors {
	private static SparseIntArray COLORS;

	private final Context context;

	private final @Getter Theme theme;
	private final @Getter int colorPrimary;
	private final @Getter int colorPrimaryDark;
	private final @Getter int textPrimary;
	private final @Getter int textSecondary;
	private final @Getter int textContrastPrimary;
	private final @Getter int textContrastSecondary;

	public ThemeColors(Context context, int color, String theme) {
		this.context = context;

		if (COLORS == null) {
			COLORS = new SparseIntArray();
			COLORS.append(getColor(R.color.red), R.array.color_red);
			COLORS.append(getColor(R.color.pink), R.array.color_pink);
			COLORS.append(getColor(R.color.purple), R.array.color_purple);
			COLORS.append(getColor(R.color.deepPurple), R.array.color_deepPurple);
			COLORS.append(getColor(R.color.indigo), R.array.color_indigo);
			COLORS.append(getColor(R.color.blue), R.array.color_blue);
			COLORS.append(getColor(R.color.lightBlue), R.array.color_lightBlue);
			COLORS.append(getColor(R.color.cyan), R.array.color_cyan);
			COLORS.append(getColor(R.color.teal), R.array.color_teal);
			COLORS.append(getColor(R.color.green), R.array.color_green);
			COLORS.append(getColor(R.color.lightGreen), R.array.color_lightGreen);
			COLORS.append(getColor(R.color.lime), R.array.color_lime);
			COLORS.append(getColor(R.color.yellow), R.array.color_yellow);
			COLORS.append(getColor(R.color.amber), R.array.color_amber);
			COLORS.append(getColor(R.color.orange), R.array.color_orange);
			COLORS.append(getColor(R.color.deepOrange), R.array.color_deepOrange);
			COLORS.append(getColor(R.color.brown), R.array.color_brown);
			COLORS.append(getColor(R.color.grey), R.array.color_grey);
			COLORS.append(getColor(R.color.blueGrey), R.array.color_blueGrey);
		}

		int white = Color.WHITE;
		int black = Color.BLACK;

		if (theme.equals("Colored")) {
			int[] colorsArray = context.getResources().getIntArray(COLORS.get(color));

			this.theme = Theme.COLORED;
			this.colorPrimary = colorsArray[0];
			this.colorPrimaryDark = colorsArray[1];
			this.textPrimary = getColor(R.color.coloredTextPrimary);
			this.textSecondary = getColor(R.color.coloredTextSecondary);
			this.textContrastPrimary = getColor(R.color.coloredTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.coloredTextContrastSecondary);
		} else if (theme.equals("White")) {
			this.theme = Theme.WHITE;
			this.colorPrimary = white;
			this.colorPrimaryDark = white;
			this.textPrimary = getColor(R.color.whiteTextPrimary);
			this.textSecondary = getColor(R.color.whiteTextSecondary);
			this.textContrastPrimary = getColor(R.color.whiteTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.whiteTextContrastSecondary);
		} else { // BLACK
			this.theme = Theme.BLACK;
			this.colorPrimary = black;
			this.colorPrimaryDark = black;
			this.textPrimary = getColor(R.color.blackTextPrimary);
			this.textSecondary = getColor(R.color.blackTextSecondary);
			this.textContrastPrimary = getColor(R.color.blackTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.blackTextContrastSecondary);
		}
	}

	private int getColor(@ColorRes int resId) {
		return ContextCompat.getColor(context, resId);
	}
}
