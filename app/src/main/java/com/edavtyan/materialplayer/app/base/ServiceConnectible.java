package com.edavtyan.materialplayer.app.base;

import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public interface ServiceConnectible {
    void unbindService();
    MusicPlayerService getService();
    boolean isBound();
    void onServiceConnected();
    void onServiceDisconnected();
}
