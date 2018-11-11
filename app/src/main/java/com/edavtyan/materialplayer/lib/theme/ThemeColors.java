package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;

import com.edavtyan.materialplayer.R;

import lombok.Getter;

public class ThemeColors {
	private final Context context;

	private final @Getter String themeStr;
	private final @Getter int theme;
	private final @Getter int themeTranslucent;

	public ThemeColors(Context context) {
		this.context = context;

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

		int primaryColor = prefs.getInt(
				context.getString(R.string.pref_colors_key),
				getColor(R.color.pref_colors_default));

		themeStr = prefs.getString(
				context.getString(R.string.pref_colorsMain_key),
				context.getString(R.string.pref_colorsMain_defaultValue));

		SparseIntArray themesColored = new SparseIntArray();
		themesColored.append(getColor(R.color.red), R.style.AppTheme_Colored_Red);
		themesColored.append(getColor(R.color.pink), R.style.AppTheme_Colored_Pink);
		themesColored.append(getColor(R.color.purple), R.style.AppTheme_Colored_Purple);
		themesColored.append(getColor(R.color.deepPurple), R.style.AppTheme_Colored_DeepPurple);
		themesColored.append(getColor(R.color.indigo), R.style.AppTheme_Colored_Indigo);
		themesColored.append(getColor(R.color.blue), R.style.AppTheme_Colored_Blue);
		themesColored.append(getColor(R.color.lightBlue), R.style.AppTheme_Colored_LightBlue);
		themesColored.append(getColor(R.color.cyan), R.style.AppTheme_Colored_Cyan);
		themesColored.append(getColor(R.color.teal), R.style.AppTheme_Colored_Teal);
		themesColored.append(getColor(R.color.green), R.style.AppTheme_Colored_Green);
		themesColored.append(getColor(R.color.lightGreen), R.style.AppTheme_Colored_LightGreen);
		themesColored.append(getColor(R.color.lime), R.style.AppTheme_Colored_Lime);
		themesColored.append(getColor(R.color.yellow), R.style.AppTheme_Colored_Yellow);
		themesColored.append(getColor(R.color.amber), R.style.AppTheme_Colored_Amber);
		themesColored.append(getColor(R.color.orange), R.style.AppTheme_Colored_Orange);
		themesColored.append(getColor(R.color.deepOrange), R.style.AppTheme_Colored_DeepOrange);
		themesColored.append(getColor(R.color.brown), R.style.AppTheme_Colored_Brown);
		themesColored.append(getColor(R.color.grey), R.style.AppTheme_Colored_Grey);
		themesColored.append(getColor(R.color.blueGrey), R.style.AppTheme_Colored_BlueGrey);
		theme = themesColored.get(primaryColor);

		SparseIntArray themesColoredTranslucent = new SparseIntArray();
		themesColoredTranslucent.append(getColor(R.color.red), R.style.AppTheme_Colored_Red_Translucent);
		themesColoredTranslucent.append(getColor(R.color.pink), R.style.AppTheme_Colored_Pink_Translucent);
		themesColoredTranslucent.append(getColor(R.color.purple), R.style.AppTheme_Colored_Purple_Translucent);
		themesColoredTranslucent.append(getColor(R.color.deepPurple), R.style.AppTheme_Colored_DeepPurple_Translucent);
		themesColoredTranslucent.append(getColor(R.color.indigo), R.style.AppTheme_Colored_Indigo_Translucent);
		themesColoredTranslucent.append(getColor(R.color.blue), R.style.AppTheme_Colored_Blue_Translucent);
		themesColoredTranslucent.append(getColor(R.color.lightBlue), R.style.AppTheme_Colored_LightBlue_Translucent);
		themesColoredTranslucent.append(getColor(R.color.cyan), R.style.AppTheme_Colored_Cyan_Translucent);
		themesColoredTranslucent.append(getColor(R.color.teal), R.style.AppTheme_Colored_Teal_Translucent);
		themesColoredTranslucent.append(getColor(R.color.green), R.style.AppTheme_Colored_Green_Translucent);
		themesColoredTranslucent.append(getColor(R.color.lightGreen), R.style.AppTheme_Colored_LightGreen_Translucent);
		themesColoredTranslucent.append(getColor(R.color.lime), R.style.AppTheme_Colored_Lime_Translucent);
		themesColoredTranslucent.append(getColor(R.color.yellow), R.style.AppTheme_Colored_Yellow_Translucent);
		themesColoredTranslucent.append(getColor(R.color.amber), R.style.AppTheme_Colored_Amber_Translucent);
		themesColoredTranslucent.append(getColor(R.color.orange), R.style.AppTheme_Colored_Orange_Translucent);
		themesColoredTranslucent.append(getColor(R.color.deepOrange), R.style.AppTheme_Colored_DeepOrange_Translucent);
		themesColoredTranslucent.append(getColor(R.color.brown), R.style.AppTheme_Colored_Brown_Translucent);
		themesColoredTranslucent.append(getColor(R.color.grey), R.style.AppTheme_Colored_Grey_Translucent);
		themesColoredTranslucent.append(getColor(R.color.blueGrey), R.style.AppTheme_Colored_BlueGrey_Translucent);
		themeTranslucent = themesColoredTranslucent.get(primaryColor);
	}

	public int getThemeRes() {
		if (themeStr.equals("Colored")) {
			return theme;
		}

		if (themeStr.equals("White")) {
			return R.style.AppTheme;
		}

		if (themeStr.equals("Black")) {
			return R.style.AppTheme_Dark;
		}

		return theme;
	}

	public int getThemeTranslucentRes() {
		if (themeStr.equals("Colored")) {
			return themeTranslucent;
		}

		if (themeStr.equals("White")) {
			return R.style.AppTheme_Translucent;
		}

		if (themeStr.equals("Black")) {
			return R.style.AppTheme_Dark_Translucent;
		}

		return themeTranslucent;
	}

	private int getColor(@ColorRes int resId) {
		return ContextCompat.getColor(context, resId);
	}
}
