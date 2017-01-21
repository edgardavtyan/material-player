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
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;

	private CompactMainScreenPref compactMainScreenPref;

	@Override
	public int getLayoutId() {
		return compactMainScreenPref.getValue()
				? R.layout.activity_main_compact
				: R.layout.activity_main;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		MainFactory factory = ((App) getApplicationContext()).getMainFactory(this);
		compactMainScreenPref = factory.getCompactMainScreenPref();

		super.onCreate(savedInstanceState);

		FragmentPagerAdapter adapter = compactMainScreenPref.getValue()
				? factory.getIconsTabsAdapter()
				: factory.getTextTabsAdapter();

		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	@Override
	public boolean isBackIconEnabled() {
		return false;
	}
}
