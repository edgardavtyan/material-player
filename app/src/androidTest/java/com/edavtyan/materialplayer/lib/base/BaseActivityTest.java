package com.edavtyan.materialplayer.lib.base;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class BaseActivityTest extends ActivityTest {
	private static MenuInflater menuInflater;

	public static class TestBaseActivity extends BaseActivity {
		@Override
		public int getLayoutId() {
			return R.layout.test_layout;
		}

		@Override
		@NonNull
		public MenuInflater getMenuInflater() {
			return BaseActivityTest.menuInflater;
		}
	}

	@Rule
	public final ActivityTestRule<TestBaseActivity> activityRule
			= new ActivityTestRule<>(TestBaseActivity.class, false, false);

	private TestBaseActivity activity;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		menuInflater = mock(MenuInflater.class);

		SharedPreferences basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		AdvancedSharedPrefs prefs = new AdvancedSharedPrefs(basePrefs);

		BaseFactory factory = mock(BaseFactory.class);
		when(factory.getNavigator()).thenReturn(navigator);
		when(factory.getPrefs()).thenReturn(prefs);
		app.setBaseFactory(factory);

		activity = activityRule.launchActivity(null);
	}

	@Test
	public void onCreate_setContentView() {
		assertThat(activity.findViewById(R.id.test_layout_root)).isNotNull();
	}

	@Test
	public void onCreateOptionsMenu_inflateMenu() {
		Menu menu = mock(Menu.class);
		activity.onCreateOptionsMenu(menu);
		verify(activity.getMenuInflater()).inflate(R.menu.menu_base, menu);
	}

	@Test
	public void onOptionsItemSelected_AudioEffectsMenuClick_gotoAudioEffectsActivity() {
		callOnOptionsItemSelectedWithMenuId(R.id.menu_effects);
		verify(navigator).gotoAudioEffects();
	}

	@Test
	public void onOptionsItemSelected_SettingsMenuClick_gotoPrefActivity() {
		callOnOptionsItemSelectedWithMenuId(R.id.menu_settings);
		verify(navigator).gotoSettings();
	}

	@Test
	public void onOptionsItemSelected_otherMenuItem_returnFalse() {
		assertThat(callOnOptionsItemSelectedWithMenuId(0)).isFalse();
	}

	private boolean callOnOptionsItemSelectedWithMenuId(int menuId) {
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(menuId);
		return activity.onOptionsItemSelected(menuItem);
	}
}
