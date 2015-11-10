package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.app.music.HQEqualizerStats;

public class Equalizer extends FrameLayout {
    public Equalizer(Context context, AttributeSet attrs) {
        super(context, attrs);

        LinearLayout bandsContainer = new LinearLayout(context, attrs);
        bandsContainer.setOrientation(LinearLayout.VERTICAL);

        for (int i = HQEqualizerStats.BANDS_COUNT - 1; i >= 0; i--) {
            bandsContainer.addView(new EqualizerBand(
                    context,
                    HQEqualizerStats.CENTER_FREQUENCIES[i],
                    0));
        }

        addView(bandsContainer);
    }
}