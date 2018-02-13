package com.edavtyan.materialplayer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.service.PlayerService;

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
	@Inject TabsAdapter tabsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);

		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);

		toolbarModule.setTitleString(null);
		toolbarModule.setBackIconEnabled(false);

		viewPager.setAdapter(tabsAdapter);
		tabLayout.setupWithViewPager(viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	protected MainComponent getComponent() {
		return DaggerMainComponent
				.builder()
				.appComponent(((App) getApplication()).getAppComponent())
				.mainModule(new MainModule(this))
				.build();
	}
}
