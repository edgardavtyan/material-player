package com.edavtyan.materialplayer.utils;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ThemeUtilsTest extends ActivityTest {
	private Activity activity;
	private AdvancedSharedPrefs prefs;
	private ThemeUtils themeUtils;

	@Override public void beforeEach() {
		super.beforeEach();
		activity = mock(Activity.class);
		doReturn(context.getResources()).when(activity).getResources();
		doReturn(context.getPackageName()).when(activity).getPackageName();
		prefs = mock(AdvancedSharedPrefs.class);
		themeUtils = new ThemeUtils(prefs);
	}

	@SuppressLint("CommitPrefEdits")
	@Test public void setTheme_setActivityThemeAndNotRecreate() {
		when(prefs.getString("pref_theme_base", "Light")).thenReturn("Dark");
		when(prefs.getString("pref_theme_primary", "Orange")).thenReturn("Blue");
		themeUtils.setTheme(activity);
		verify(activity).setTheme(R.style.AppTheme_Dark_Blue);
		verify(activity, never()).recreate();
	}

	@Test public void setThemeAndRecreate__baseThemeChanged_applyThemeAndRecreateActivity() {
		themeUtils.setThemeAndRecreate(activity, "pref_theme_base");
		verify(activity).setTheme(anyInt());
		verify(activity).recreate();
	}

	@Test public void setThemeAndRecreate__primaryThemeChanged_applyThemeAndRecreateActivity() {
		themeUtils.setThemeAndRecreate(activity, "pref_theme_primary");
		verify(activity).setTheme(anyInt());
		verify(activity).recreate();
	}

	@Test public void setThemeAndRecreate__noThemeChanged_notApplyThemeToActivity() {
		themeUtils.setThemeAndRecreate(activity, "no_theme_changed");
		verify(activity, never()).setTheme(anyInt());
		verify(activity, never()).recreate();
	}
}
