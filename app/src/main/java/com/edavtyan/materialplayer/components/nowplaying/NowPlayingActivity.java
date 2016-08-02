package com.edavtyan.materialplayer.components.nowplaying;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingArt;
import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingControls;
import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingFab;
import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingInfo;
import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingSeekbar;
import com.edavtyan.materialplayer.components.playlist.PlaylistActivity;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;

import lombok.Getter;

public class NowPlayingActivity extends BaseToolbarActivity implements ServiceConnection {

	private NowPlayingPresenter presenter;
	private @Getter NowPlayingInfo info;
	private @Getter NowPlayingControls controls;
	private @Getter NowPlayingArt art;
	private @Getter NowPlayingSeekbar seekbar;
	private @Getter NowPlayingFab fab;

	public static void startActivity(Context context) {
		context.startActivity(new Intent(context, NowPlayingActivity.class));
	}

	public void openPlaylist() {
		PlaylistActivity.startActivity(this);
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.nowplaying_toolbar_title;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_nowplaying_2;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new NowPlayingPresenter();
		info = new NowPlayingInfo(this);
		controls = new NowPlayingControls(this, presenter);
		art = new NowPlayingArt(this);
		seekbar = new NowPlayingSeekbar(this, presenter);
		fab = new NowPlayingFab(this, presenter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		bindService(
				new Intent(this, MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		MusicPlayerService service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
		presenter.bind(this, new NowPlayingModel(service));
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
