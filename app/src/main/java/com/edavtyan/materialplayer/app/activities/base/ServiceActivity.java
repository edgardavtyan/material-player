package com.edavtyan.materialplayer.app.activities.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public abstract class ServiceActivity extends AppCompatActivity implements ServiceConnection {
    private MusicPlayerService service;
    private boolean isBound;

    /*
     * Fragment
     */

    @Override
    public void onResume() {
        super.onResume();
        bindService(
                new Intent(this, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService(this);
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerBinder)iBinder).getService();
        isBound = true;
        onServiceConnected();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }

    /*
     * Public methods
     */

    public void onServiceConnected() {}

    public MusicPlayerService getService() {
        return service;
    }

    public boolean isBound() {
        return isBound;
    }

}
