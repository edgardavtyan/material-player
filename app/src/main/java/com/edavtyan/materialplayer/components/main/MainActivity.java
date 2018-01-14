package com.edavtyan.materialplayer.components.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;

	@Inject CompactMainScreenPref compactMainScreenPref;
	@Inject IconsTabsAdapter iconsTabsAdapter;
	@Inject TextTabsAdapter textTabsAdapter;

	private boolean isCompactModeEnabled;

	@Override
	public int getLayoutId() {
		return compactMainScreenPref.isEnabled()
				? R.layout.activity_main_compact
				: R.layout.activity_main;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getComponent().inject(this);

		isCompactModeEnabled = compactMainScreenPref.isEnabled();

		super.onCreate(savedInstanceState);

		ActivityToolbarModule toolbarModule = new ActivityToolbarModule(this);
		toolbarModule.setBackIconEnabled(false);
		toolbarModule.setTitleStringId(isCompactModeEnabled ? R.string.empty : R.string.app_name);
		addModule(toolbarModule);

		FragmentPagerAdapter adapter = compactMainScreenPref.isEnabled()
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
				.mainModule(new MainModule(this))
				.build();
	}
}
