package com.edavtyan.materialplayer.components.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.utils.WindowUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {
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

		FragmentPagerAdapter adapter = compactMainScreenPref.isEnabled()
				? iconsTabsAdapter : textTabsAdapter;

		if (isCompactModeEnabled && WindowUtils.isPortrait(this)) {
			toolbar.setTitle(null);
		}

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

	@Override
	public boolean isBackIconEnabled() {
		return false;
	}

	protected MainComponent getComponent() {
		return DaggerMainComponent
				.builder()
				.mainModule(new MainModule(this))
				.build();
	}
}
