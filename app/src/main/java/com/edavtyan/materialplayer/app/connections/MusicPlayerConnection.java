package com.edavtyan.materialplayer.app.connections;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class MusicPlayerConnection implements ServiceConnection {
    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerService playerService;
    private boolean isBound;

    /* ************************* */
    /* ServiceConnection members */
    /* ************************* */

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MusicPlayerService.MusicPlayerBinder binder = (MusicPlayerService.MusicPlayerBinder) iBinder;
        playerService = binder.getService();
        isBound = true;
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public boolean isBound() {
        return isBound;
    }

    public MusicPlayerService getService() {
        return playerService;
    }
}
