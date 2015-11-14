package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;

import lombok.Setter;

public class TitledSeekbarView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekbar;
    private TextView titleView;
    private @Setter OnSeekChangedListener onSeekBarChangeListener;


    public interface OnSeekChangedListener {
        void onProgressChanged(TitledSeekbarView titledSeekbar, int progress, boolean fromUser);
        void onStopTrackingTouch(TitledSeekbarView titledSeekbar);
    }


    public TitledSeekbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_titled_seekbar, this);

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(this);

        titleView = (TextView) findViewById(R.id.title);
    }


    public void setProgress(int progress) {
        seekbar.setProgress(progress);
    }

    public void setMax(int max) {
        seekbar.setMax(max);
    }

    public void setText(int title) {
        titleView.setText(title);
    }


    @Override
    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(this, progress, fromUser);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekbar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekbar) {
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }
}
