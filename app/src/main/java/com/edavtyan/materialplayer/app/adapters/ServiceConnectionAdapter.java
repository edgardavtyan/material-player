package com.edavtyan.materialplayer.app.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public abstract class ServiceConnectionAdapter<TViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<TViewHolder>
        implements ServiceConnection {

    private MusicPlayerService service;
    private Context context;
    private boolean isBound;

    public ServiceConnectionAdapter(Context context) {
        this.context = context;
        context.bindService(
                new Intent(context, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerBinder)iBinder).getService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }

    /*
     * Public methods
     */

    public void unbindFromService() {
        context.unbindService(this);
    }

    public MusicPlayerService getService() {
        return service;
    }

    public boolean isBound() {
        return isBound;
    }
}
