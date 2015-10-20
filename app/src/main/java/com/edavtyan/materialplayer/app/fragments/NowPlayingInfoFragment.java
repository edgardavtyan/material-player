package com.edavtyan.materialplayer.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.fragments.base.ServiceFragment;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class NowPlayingInfoFragment extends ServiceFragment {
    private AQuery aquery;

    /*
     * BroadcastReceivers
     */

    private final BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncWithService();
        }
    };

    /*
     * ServiceFragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    }

    @Override
    public void onServiceConnected() {
        syncWithService();
    }

    /*
     * Private methods
     */

    private void syncWithService() {
        String trackMetadata = getResources().getString(
                R.string.nowplaying_info_pattern,
                getService().getMetadata().getArtistTitle(),
                getService().getMetadata().getAlbumTitle());

        aquery.id(R.id.title).text(getService().getMetadata().getTrackTitle());
        aquery.id(R.id.info).text(trackMetadata);
    }
}
