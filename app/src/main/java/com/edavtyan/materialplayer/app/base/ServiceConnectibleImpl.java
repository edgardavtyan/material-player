package com.edavtyan.materialplayer.app.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class ServiceConnectibleImpl
        implements ServiceConnectible,
                   ServiceConnection {

    private Context context;
    private MusicPlayerService service;
    private boolean isBound;


    public ServiceConnectibleImpl(Context context) {
        this.context = context;
    }


    @Override
    public void bindService() {
        context.bindService(
                new Intent(context, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public final void unbindService() {
        context.unbindService(this);
    }

    @Override
    public final boolean isBound() {
        return isBound;
    }

    @Override
    public final MusicPlayerService getService() {
        return service;
    }

    @Override
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerService.MusicPlayerBinder)iBinder).getService();
        isBound = true;
        onServiceConnected();
    }

    @Override
    public final void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        onServiceDisconnected();
    }

    @Override
    public void onServiceConnected() {}

    @Override
    public void onServiceDisconnected() {}
}
