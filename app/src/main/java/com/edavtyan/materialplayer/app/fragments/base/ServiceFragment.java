package com.edavtyan.materialplayer.app.fragments.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public abstract class ServiceFragment extends Fragment implements ServiceConnection {
    private MusicPlayerService service;
    private boolean isBound;

    /*
     * Fragment
     */

    @Override
    public void onResume() {
        super.onResume();
        getActivity().bindService(
                new Intent(getActivity(), MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
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
