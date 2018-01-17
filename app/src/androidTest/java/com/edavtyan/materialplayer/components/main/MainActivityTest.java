package com.edavtyan.materialplayer.components.main;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class MainActivityTest extends ActivityTest {
	private static CompactMainScreenPref compactMainScreenPref;
	private static ActivityToolbarModule toolbarModule;
	private static ActivityBaseMenuModule baseMenuModule;
	private static ScreenThemeModule themeModule;
	private static IconsTabsAdapter iconsTabsAdapter;
	private static TextTabsAdapter textTabsAdapter;

	public static class TestMainActivity extends MainActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			compactMainScreenPref = MainActivityTest.compactMainScreenPref;
			toolbarModule = MainActivityTest.toolbarModule;
			baseMenuModule = MainActivityTest.baseMenuModule;
			themeModule = MainActivityTest.themeModule;
			iconsTabsAdapter = MainActivityTest.iconsTabsAdapter;
			textTabsAdapter = MainActivityTest.textTabsAdapter;
			super.onCreate(savedInstanceState);
		}

		@Override
		public ComponentName startService(Intent service) {
			return null;
		}

		@Override
		protected MainComponent getComponent() {
			return mock(MainComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<TestMainActivity> activityRule
			= new ActivityTestRule<>(TestMainActivity.class, false, false);

	private TestMainActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		toolbarModule = mock(ActivityToolbarModule.class);
		baseMenuModule = mock(ActivityBaseMenuModule.class);
		themeModule = mock(ScreenThemeModule.class);
		compactMainScreenPref = mock(CompactMainScreenPref.class);
		iconsTabsAdapter = mock(IconsTabsAdapter.class);
		textTabsAdapter = mock(TextTabsAdapter.class);
	}

	@Test
	public void onCreate_compactModeDisabled_setContentViewToNormal() {
		when(compactMainScreenPref.isEnabled()).thenReturn(false);
		activity = activityRule.launchActivity(null);
		View rootView = activity.findViewById(R.id.root_main_normal);
		assertThat(rootView).isNotNull();
	}

	@Test
	public void onCreate_compactModeEnabled_setContentViewToCompact() {
		when(compactMainScreenPref.isEnabled()).thenReturn(true);
		activity = activityRule.launchActivity(null);
		View rootView = activity.findViewById(R.id.root_main_compact);
		assertThat(rootView).isNotNull();
	}

	@Test
	public void onCreate_addModules() {
		activityRule.launchActivity(null);
		verify(toolbarModule).onCreate();
		verify(baseMenuModule).onCreate();
		verify(themeModule).onCreate();
	}

	@Test
	public void onCreate_setupToolbar() {
		activityRule.launchActivity(null);
		verify(toolbarModule).setBackIconEnabled(false);
		verify(toolbarModule).setTitleString(null);
	}

	@Test
	public void onCreate_compactModeDisabled_useTextTabs() {
		when(compactMainScreenPref.isEnabled()).thenReturn(false);
		activity = activityRule.launchActivity(null);
		ViewPager viewPager = (ViewPager) activity.findViewById(R.id.view_pager);
		assertThat(viewPager.getAdapter()).isSameAs(textTabsAdapter);
	}

	@Test
	public void onCreate_compactModeEnabled_useIconsTabs() {
		when(compactMainScreenPref.isEnabled()).thenReturn(true);
		activity = activityRule.launchActivity(null);
		ViewPager viewPager = (ViewPager) activity.findViewById(R.id.view_pager);
		assertThat(viewPager.getAdapter()).isSameAs(iconsTabsAdapter);
	}
}
