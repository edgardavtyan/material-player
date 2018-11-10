package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;

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
	private final @Getter int background;

	public ThemeColors(Context context) {
		this.context = context;

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

		int primaryColor = prefs.getInt(
				context.getString(R.string.pref_colors_key),
				getColor(R.color.pref_colors_default));

		String theme = prefs.getString(
				context.getString(R.string.pref_colorsMain_key),
				context.getString(R.string.pref_colorsMain_defaultValue));

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

		switch (theme) {
		case "Colored":
			int[] colorsArray = context.getResources().getIntArray(COLORS.get(primaryColor));
			this.theme = Theme.COLORED;
			this.colorPrimary = colorsArray[0];
			this.colorPrimaryDark = colorsArray[1];
			this.textPrimary = getColor(R.color.coloredTextPrimary);
			this.textSecondary = getColor(R.color.coloredTextSecondary);
			this.textContrastPrimary = getColor(R.color.coloredTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.coloredTextContrastSecondary);
			this.background = getColor(R.color.white);
			break;
		case "White":
			this.theme = Theme.WHITE;
			this.colorPrimary = white;
			this.colorPrimaryDark = white;
			this.textPrimary = getColor(R.color.whiteTextPrimary);
			this.textSecondary = getColor(R.color.whiteTextSecondary);
			this.textContrastPrimary = getColor(R.color.whiteTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.whiteTextContrastSecondary);
			this.background = getColor(R.color.white);
			break;
		default:  // BLACK
			this.theme = Theme.BLACK;
			this.colorPrimary = black;
			this.colorPrimaryDark = black;
			this.textPrimary = getColor(R.color.blackTextPrimary);
			this.textSecondary = getColor(R.color.blackTextSecondary);
			this.textContrastPrimary = getColor(R.color.blackTextContrastPrimary);
			this.textContrastSecondary = getColor(R.color.blackTextContrastSecondary);
			this.background = getColor(R.color.black);
			break;
		}
	}

	public void colorizeMenu(Menu menu) {
		for (int i = 0; i < menu.size(); i++) {
			MenuItem item = menu.getItem(i);
			Drawable icon = item.getIcon();

			if (icon != null) {
				icon.setColorFilter(getTextContrastPrimary(), PorterDuff.Mode.SRC_IN);
			}
		}
	}

	private int getColor(@ColorRes int resId) {
		return ContextCompat.getColor(context, resId);
	}
}
