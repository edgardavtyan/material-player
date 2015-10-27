package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.app.base.ServiceConnectible;
import com.edavtyan.materialplayer.app.base.ServiceConnectibleImpl;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public abstract class RecyclerViewServiceAdapter<TViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<TViewHolder>
        implements ServiceConnectible {

    private ServiceConnectibleImpl serviceConnectible;


    public RecyclerViewServiceAdapter(Context context) {
        serviceConnectible = new ServiceConnectibleImpl(context);
    }


    @Override
    public void bindService() {
        serviceConnectible.bindService();
    }

    @Override
    public final void unbindService() {
        serviceConnectible.unbindService();
    }

    @Override
    public final MusicPlayerService getService() {
        return serviceConnectible.getService();
    }

    @Override
    public final boolean isBound() {
        return serviceConnectible.isBound();
    }

    @Override
    public void onServiceDisconnected() {}

    @Override
    public void onServiceConnected() {}
}
