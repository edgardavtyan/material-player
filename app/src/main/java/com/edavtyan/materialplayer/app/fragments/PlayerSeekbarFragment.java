package com.edavtyan.materialplayer.app.fragments;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.aquery.SeekbarQuery;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;

public class PlayerSeekbarFragment
        extends Fragment
        implements SeekBar.OnSeekBarChangeListener, ServiceConnection {

    private SeekbarQuery seekbarQuery;
    private Handler handler;
    private MusicPlayerService service;
    private Runnable syncSeekbar = new Runnable() {
        @Override
        public void run() {
            seekbarQuery.value(service.getPosition());
            handler.postDelayed(syncSeekbar, 1000);
        }
    };

    /*
     * BroadcastReceivers
     */

    private BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncSeekbar.run();
        }
    };
    private BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handler.removeCallbacks(syncSeekbar);
        }
    };
    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            seekbarQuery.max(service.getDuration());
            syncSeekbar.run();
        }
    };

    /*
     * Fragment members
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater
                .from(getContext())
                .inflate(R.layout.fragment_seekbar, container);

        seekbarQuery = new SeekbarQuery(view).id(R.id.seekbar);
        seekbarQuery.changed(this);
        handler = new Handler();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        getActivity().getApplicationContext().bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onResume() {
        getActivity().registerReceiver(
                playReceiver,
                new IntentFilter(MusicPlayerService.SEND_PLAY));
        getActivity().registerReceiver(
                pauseReceiver,
                new IntentFilter(MusicPlayerService.SEND_PAUSE));
        getActivity().registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(playReceiver);
        getActivity().unregisterReceiver(pauseReceiver);
        getActivity().unregisterReceiver(newTrackReceiver);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        getActivity().getApplicationContext().unbindService(this);
        super.onDestroy();
    }

    /*
     * ServiceConnection members
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerBinder)iBinder).getService();
        seekbarQuery.max(service.getDuration());
        syncSeekbar.run();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {}

    /*
     * SeekBar.OnSeekBarChangeListener members
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(syncSeekbar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        service.setPosition(seekBar.getProgress());
    }
}
