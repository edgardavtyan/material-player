package com.edavtyan.materialplayer.app.fragments;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public class NowPlayingInfoFragment
        extends Fragment
        implements ServiceConnection {
    private AQuery aquery;
    private MusicPlayerService service;

    /*
     * BroadcastReceivers
     */

    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncWithService();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nowplaying_info, container, false);
        aquery = new AQuery(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
        getActivity().bindService(
                new Intent(getActivity(), MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(newTrackReceiver);
        getActivity().unbindService(this);
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerBinder)iBinder).getService();
        syncWithService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {}

    /*
     * Private methods
     */

    private void syncWithService() {
        String trackMetadata = getResources().getString(
                R.string.nowplaying_info_pattern,
                service.getMetadata().getArtistTitle(),
                service.getMetadata().getAlbumTitle());

        aquery.id(R.id.title).text(service.getMetadata().getTrackTitle());
        aquery.id(R.id.info).text(trackMetadata);
    }
}
