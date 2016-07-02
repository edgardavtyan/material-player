package com.edavtyan.materialplayer.lib.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.MusicPlayerService.MusicPlayerBinder;

public abstract class ServiceFragment extends Fragment implements ServiceConnection {
	private MusicPlayerService service;
	private boolean isBound;

	/*
	 * Fragment
	 */

	@Override
	public void onResume() {
		super.onResume();
		getActivity().bindService(new Intent(getActivity(), MusicPlayerService.class), this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onPause() {
		super.onPause();
		getActivity().unbindService(this);
	}

	/*
	 * ServiceConnection
	 */

	@Override
	public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
		service = ((MusicPlayerBinder) iBinder).getService();
		isBound = true;
		onServiceConnected();
	}

	@Override
	public final void onServiceDisconnected(ComponentName componentName) {
		isBound = false;
		onServiceDisconnected();
	}

	/*
	 * Public methods
	 */

	public void onServiceConnected() {
	}

	public void onServiceDisconnected() {
	}

	public MusicPlayerService getService() {
		return service;
	}

	public boolean isBound() {
		return isBound;
	}

}
