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

public class MainActivity extends BaseToolbarActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Toolbar toolbar = findView(R.id.toolbar);
		if (WindowUtils.isPortrait(this)) {
			toolbar.setTitle(null);
		}

		FragmentPagerAdapter adapter = new IconsTabsAdapter(getSupportFragmentManager(), this);

		ViewPager viewPager = findView(R.id.view_pager);
		viewPager.setAdapter(adapter);

		TabLayout tabLayout = findView(R.id.tab_layout);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	/* BaseToolbarActivity */

	@Override
	public boolean isBackIconEnabled() {
		return false;
	}
}
