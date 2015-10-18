package com.edavtyan.materialplayer.app.controls;

import android.os.Handler;
import android.widget.SeekBar;

public class SeekbarControl {
    private Handler handler;
    private OnStopTrackingListener onStopListener;
    private OnUpdateListener updater;
    private SeekBar seekbar;
    private Runnable action = new Runnable() {
        @Override
        public void run() {
            updater.run(seekbar);
            handler.postDelayed(this, 1000);
        }
    };


    public SeekbarControl(SeekBar seekbar) {
        this.handler = new Handler();
        this.seekbar = seekbar;
        this.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(action);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onStopListener.run(seekBar.getProgress());
                action.run();
            }
        });
    }


    public interface OnStopTrackingListener {
        void run(int position);
    }

    public interface OnUpdateListener {
        void run(SeekBar seekBar);
    }


    public void setUpdater(OnUpdateListener updater) {
        this.updater = updater;
    }

    public void setOnStopTrackingListener(OnStopTrackingListener listener) {
        onStopListener = listener;
    }

    public void start() {
        action.run();
    }

    public void setMax(int max) {
        seekbar.setMax(max);
    }
}
