package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.app.base.ServiceConnectible;
import com.edavtyan.materialplayer.app.base.ServiceConnectibleImpl;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public abstract class RecyclerServiceCursorAdapter<TViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerViewCursorAdapter<TViewHolder>
        implements ServiceConnectible {

    private final ServiceConnectibleImpl serviceConnectible;

    public RecyclerServiceCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        serviceConnectible = new ServiceConnectibleImpl(context);
    }

    /*
     * ServiceConnectible
     */

    @Override
    public void bindService() {
        serviceConnectible.bindService();
    }

    @Override
    public void unbindService() {
        serviceConnectible.unbindService();
    }

    @Override
    public MusicPlayerService getService() {
        return serviceConnectible.getService();
    }

    @Override
    public boolean isBound() {
        return serviceConnectible.isBound();
    }

    @Override
    public void onServiceConnected() {}

    @Override
    public void onServiceDisconnected() {}
}
