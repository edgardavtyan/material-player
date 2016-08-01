package com.edavtyan.materialplayer.components.nowplaying2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;

import lombok.Getter;

public class NowPlayingActivity2 extends BaseToolbarActivity implements ServiceConnection {

	private NowPlayingPresenter presenter;
	private @Getter NowPlayingInfoView infoView;

	public static void startActivity(Context context) {
		context.startActivity(new Intent(context, NowPlayingActivity2.class));
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new NowPlayingPresenter();
		infoView = new NowPlayingInfoView(this);
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
	public int getLayoutId() {
		return R.layout.activity_nowplaying_2;
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.nowplaying_toolbar_title;
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
