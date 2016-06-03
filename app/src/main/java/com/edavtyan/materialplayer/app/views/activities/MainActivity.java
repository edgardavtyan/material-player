package com.edavtyan.materialplayer.app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.lib.activities.BaseActivity;
import com.edavtyan.materialplayer.app.lib.adapters.TabPagerFragmentAdapter;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class MainActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.app_name);
		setSupportActionBar(toolbar);

		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(new TabPagerFragmentAdapter(getSupportFragmentManager()));

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, MusicPlayerService.class);
		startService(intent);
	}
}
