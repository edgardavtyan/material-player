package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistDetailActivity extends BaseActivity {
	public static final String EXTRA_PLAYLIST_NAME = "extra_playlistName";

	@BindView(R.id.list) RecyclerView list;

	@Inject PlaylistDetailAdapter adapter;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlist);
		getComponent().inject(this);
		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);
		ButterKnife.bind(this);

		list.setLayoutManager(new LinearLayoutManager(this));
		list.setAdapter(adapter);
	}

	private PlaylistDetailFactoryComponent getComponent() {
		String playlistName = getIntent().getStringExtra(EXTRA_PLAYLIST_NAME);
		return DaggerPlaylistDetailFactoryComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.playlistDetailFactory(new PlaylistDetailFactory(this, playlistName))
				.build();
	}

}
