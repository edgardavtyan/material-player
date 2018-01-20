package com.edavtyan.materialplayer.components.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ModularActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	@Inject CompactMainScreenPref compactMainScreenPref;
	@Inject IconsTabsAdapter iconsTabsAdapter;
	@Inject TextTabsAdapter textTabsAdapter;

	private boolean isCompactModeEnabled;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);

		isCompactModeEnabled = compactMainScreenPref.isEnabled();
		setContentView(isCompactModeEnabled
				? R.layout.activity_main_compact : R.layout.activity_main);
		ButterKnife.bind(this);

		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);

		toolbarModule.setTitleString(null);
		toolbarModule.setBackIconEnabled(false);

		FragmentPagerAdapter adapter = isCompactModeEnabled
				? iconsTabsAdapter : textTabsAdapter;

		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isCompactModeEnabled != compactMainScreenPref.isEnabled()) {
			this.recreate();
		}
	}

	protected MainComponent getComponent() {
		return DaggerMainComponent
				.builder()
				.appComponent(((App)getApplication()).getAppComponent())
				.mainModule(new MainModule(this))
				.activityModulesFactory(new ActivityModulesFactory())
				.build();
	}
}
