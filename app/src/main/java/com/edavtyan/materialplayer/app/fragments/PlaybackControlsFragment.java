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

public class PlaybackControlsFragment
        extends ServiceFragment
        implements View.OnClickListener {

    private AQuery aquery;

    /*
     * BroadcastReceivers
     */

    private BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            aquery.id(R.id.play_pause).image(R.drawable.ic_pause_white_36dp);
        }
    };
    private BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            aquery.id(R.id.play_pause).image(R.drawable.ic_play_white_36dp);
        }
    };
    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            aquery.id(R.id.play_pause).image(R.drawable.ic_pause_white_36dp);
        }
    };

    /*
     * ServiceFragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playback_controls, container, false);

        aquery = new AQuery(view);
        aquery.id(R.id.play_pause).clicked(this);
        aquery.id(R.id.fast_forward).clicked(this);
        aquery.id(R.id.rewind).clicked(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(
                playReceiver,
                new IntentFilter(MusicPlayerService.SEND_PLAY));
        getActivity().registerReceiver(
                pauseReceiver,
                new IntentFilter(MusicPlayerService.SEND_PAUSE));
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
        getActivity().unregisterReceiver(playReceiver);
        getActivity().unregisterReceiver(pauseReceiver);
        getActivity().unregisterReceiver(newTrackReceiver);
        getActivity().unbindService(this);
    }

    @Override
    public void onServiceConnected() {
        if (getService().isPlaying()) {
            aquery.id(R.id.play_pause).image(R.drawable.ic_pause_white_36dp);
        } else {
            aquery.id(R.id.play_pause).image(R.drawable.ic_play_white_36dp);
        }
    }

    /*
     * View.OnClickListener members
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rewind:
                getActivity().sendBroadcast(new Intent(MusicPlayerService.ACTION_REWIND));
                break;

            case R.id.play_pause:
                getActivity().sendBroadcast(new Intent(MusicPlayerService.ACTION_PLAY_PAUSE));
                break;

            case R.id.fast_forward:
                getActivity().sendBroadcast(new Intent(MusicPlayerService.ACTION_FAST_FORWARD));
                break;
        }
    }
}
