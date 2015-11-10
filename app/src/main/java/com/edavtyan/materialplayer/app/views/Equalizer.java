package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.app.music.HQEqualizerStats;

public class Equalizer extends FrameLayout {
    public Equalizer(Context context, AttributeSet attrs) {
        super(context, attrs);

        int bandsCount;
        int[] frequencies;

        if (isInEditMode()) {
            bandsCount = 10;
            frequencies = new int[] {
                    31250, 62500, 125000, 250000, 500000, 1000000,
                    2000000, 4000000, 8000000, 16000000
            };
        } else {
            bandsCount = HQEqualizerStats.BANDS_COUNT;
            frequencies = HQEqualizerStats.CENTER_FREQUENCIES;
        }

        LinearLayout bandsContainer = new LinearLayout(context, attrs);
        bandsContainer.setOrientation(LinearLayout.VERTICAL);

        for (int i = bandsCount- 1; i >= 0; i--) {
            bandsContainer.addView(new EqualizerBand( context, frequencies[i], 0));
        }

        addView(bandsContainer);
    }
}