package com.edavtyan.materialplayer.lib.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.MusicPlayerService;

public abstract class RecyclerViewServiceAdapter<TViewHolder extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<TViewHolder> implements ServiceConnection {
	protected Context context;
	protected MusicPlayerService service;
	protected boolean isBound;

	public RecyclerViewServiceAdapter(Context context) {
		this.context = context;
	}

	/*
	 * ServiceConnection
	 */

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
		isBound = true;
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		isBound = false;
	}

	/*
	 * Public methods
	 */

	public void bindService() {
		context.bindService(
				new Intent(context, MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	public final void unbindService() {
		context.unbindService(this);
	}
}
