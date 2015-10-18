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
import android.widget.ImageButton;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public class PlaybackControlsFragment
        extends Fragment
        implements View.OnClickListener, ServiceConnection {
    private ImageButton playPauseButton;
    private ImageButton fastForwardButton;
    private ImageButton rewindButton;

    /*
     * BroadcastReceivers
     */

    private BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white_36dp);
        }
    };
    private BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_play_white_36dp);
        }
    };
    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white_36dp);
        }
    };

    /*
     * Fragment members
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.fragment_playback_controls, container, false);

        playPauseButton = (ImageButton) view.findViewById(R.id.play_pause);
        playPauseButton.setOnClickListener(this);
        fastForwardButton = (ImageButton) view.findViewById(R.id.fast_forward);
        fastForwardButton.setOnClickListener(this);
        rewindButton = (ImageButton) view.findViewById(R.id.rewind);
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
        getActivity().unbindService(this);
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

    /*
     * ServiceConnection members
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MusicPlayerService service = ((MusicPlayerBinder)iBinder).getService();
        if (service.isPlaying()) {
            playPauseButton.setImageResource(R.drawable.ic_pause_white_36dp);
        } else {
            playPauseButton.setImageResource(R.drawable.ic_play_white_36dp);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}
