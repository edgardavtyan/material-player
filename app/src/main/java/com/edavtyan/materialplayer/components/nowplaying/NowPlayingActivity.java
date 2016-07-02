package com.edavtyan.materialplayer.components.nowplaying;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.utils.DeviceUtils;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;
import com.edavtyan.materialplayer.components.playlist.PlaylistActivity;

public class NowPlayingActivity extends BaseToolbarActivity {
	public static void startActivity(Context context) {
		Intent intent = new Intent(context, NowPlayingActivity.class);
		context.startActivity(intent);
	}

	//---

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (DeviceUtils.isPortrait(getResources())) {
			FloatingActionButton playlistFab = (FloatingActionButton) findViewById(R.id.fab_playlist);
			playlistFab.setOnClickListener(view -> PlaylistActivity.startActivity(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		if (DeviceUtils.isLandscape(getResources())) {
			getMenuInflater().inflate(R.menu.menu_playlist, menu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_playlist:
			PlaylistActivity.startActivity(this);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_nowplaying;
	}

	/* BaseToolbarActivity */

	@Override
	public int getToolbarTitleStringId() {
		return R.string.nowplaying_toolbar_title;
	}
}
