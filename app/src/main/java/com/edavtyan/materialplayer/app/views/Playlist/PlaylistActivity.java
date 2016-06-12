package com.edavtyan.materialplayer.app.views.playlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.utils.AppColors;
import com.edavtyan.materialplayer.app.views.lib.activities.BaseToolbarActivity;
import com.edavtyan.materialplayer.app.views.lib.decorators.DividerItemDecoration;

public class PlaylistActivity extends BaseToolbarActivity {
	private PlaylistAdapter playlistAdapter;
	private AppColors appColors;

	//---

	public static void startActivity(Context context) {
		context.startActivity(new Intent(context, PlaylistActivity.class));
	}

	//---

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appColors = new AppColors(this);
		playlistAdapter = new PlaylistAdapter(this);

		RecyclerView playlistView = (RecyclerView) findViewById(R.id.list);
		playlistView.setLayoutManager(new LinearLayoutManager(this));
		playlistView.setAdapter(playlistAdapter);
		playlistView.addItemDecoration(new DividerItemDecoration(appColors.divider));

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		super.onResume();
		playlistAdapter.registerReceivers();
		playlistAdapter.bindService();
	}

	@Override
	public void onPause() {
		super.onPause();
		playlistAdapter.unbindService();
		playlistAdapter.unregisterReceivers();
	}

	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_playlist;
	}

	/* BaseToolbarActivity */

	@Override
	public int getToolbarTitleStringId() {
		return R.string.queue_title;
	}
}
