package com.edavtyan.materialplayer.app.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.edavtyan.materialplayer.app.MusicPlayerService;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.lib.activities.BaseToolbarActivity;
import com.edavtyan.materialplayer.app.views.lib.adapters.TabPagerFragmentAdapter;

public class MainActivity extends BaseToolbarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(new TabPagerFragmentAdapter(getSupportFragmentManager()));

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, MusicPlayerService.class);
		startService(intent);
	}

	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	/* BaseToolbarActivity */

	@Override
	protected boolean isBackIconEnabled() {
		return false;
	}
}
