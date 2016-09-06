package com.edavtyan.materialplayer.components.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.adapters.TabPagerFragmentAdapter;

public class MainActivity extends BaseToolbarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewPager viewPager = findView(R.id.view_pager);
		viewPager.setAdapter(new TabPagerFragmentAdapter(getSupportFragmentManager()));

		TabLayout tabLayout = findView(R.id.tab_layout);
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
