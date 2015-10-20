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
import android.widget.ImageButton;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.fragments.base.ServiceFragment;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class PlaybackControlsFragment
        extends ServiceFragment
        implements View.OnClickListener {

    ImageButton playPauseButton;
    ImageButton fastForwardButton;
    ImageButton rewindButton;

    /*
     * BroadcastReceivers
     */

    private final BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white);
        }
    };
    private final BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_play_white);
        }
    };
    private final BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white);
        }
    };

    /*
     * ServiceFragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playback_controls, container, false);

        playPauseButton =(ImageButton) view.findViewById(R.id.play_pause);
        playPauseButton.setOnClickListener(this);

        fastForwardButton =(ImageButton) view.findViewById(R.id.fast_forward);
        fastForwardButton.setOnClickListener(this);

        rewindButton =(ImageButton) view.findViewById(R.id.rewind);
        rewindButton.setOnClickListener(this);


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
    }

    @Override
    public void onServiceConnected() {
        if (getService().isPlaying()) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white);
        } else {
            playPauseButton.setImageResource(R.drawable.ic_play_white);
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
