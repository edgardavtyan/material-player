package com.edavtyan.materialplayer.ui.main;

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

@SuppressLint("StaticFieldLeak")
public class MainActivityTest extends ActivityTest {
	private static ActivityToolbarModule toolbarModule;
	private static ActivityBaseMenuModule baseMenuModule;
	private static ScreenThemeModule themeModule;
	private static TabsAdapter tabsAdapter;

	public static class TestMainActivity extends MainActivity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			toolbarModule = MainActivityTest.toolbarModule;
			baseMenuModule = MainActivityTest.baseMenuModule;
			themeModule = MainActivityTest.themeModule;
			tabsAdapter = MainActivityTest.tabsAdapter;
			super.onCreate(savedInstanceState);
		}

		@Override
		public ComponentName startService(Intent service) {
			return null;
		}

		@Override
		protected MainDIComponent getComponent() {
			return mock(MainDIComponent.class);
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
		tabsAdapter = mock(TabsAdapter.class);
	}

	@Test
	public void onCreate_setContentView() {
		activity = activityRule.launchActivity(null);
		View rootView = activity.findViewById(R.id.root_main);
		assertThat(rootView).isNotNull();
	}

	@Test
	public void onCreate_addModules() {
		activityRule.launchActivity(null);
		verify(toolbarModule).onCreate(null);
		verify(baseMenuModule).onCreate(null);
		verify(themeModule).onCreate(null);
	}

	@Test
	public void onCreate_setupToolbar() {
		activityRule.launchActivity(null);
		verify(toolbarModule).setBackIconEnabled(false);
		verify(toolbarModule).setTitleString("");
	}

	@Test
	public void onCreate_addTabsAdapter() {
		activity = activityRule.launchActivity(null);
		ViewPager viewPager = (ViewPager) activity.findViewById(R.id.view_pager);
		assertThat(viewPager.getAdapter()).isSameAs(tabsAdapter);
	}
}
