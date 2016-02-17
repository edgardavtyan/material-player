package com.edavtyan.materialplayer.app.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public abstract class RecyclerServiceCursorAdapter<TViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerViewCursorAdapter<TViewHolder>
        implements ServiceConnection {

    protected MusicPlayerService service;
    protected boolean isBound;

    public RecyclerServiceCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    /*
     * ServiceConnectible
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

    public void unbindService() {
        context.unbindService(this);
    }
}
