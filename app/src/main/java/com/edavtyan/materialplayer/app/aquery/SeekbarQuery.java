package com.edavtyan.materialplayer.app.aquery;

import android.app.Activity;
import android.view.View;
import android.widget.SeekBar;

import com.androidquery.AbstractAQuery;

public class SeekbarQuery extends AbstractAQuery<SeekbarQuery> {
    public SeekbarQuery(Activity act) {
        super(act);
    }

    public SeekbarQuery(View view) {
        super(view);
    }


    public SeekbarQuery changed(SeekBar.OnSeekBarChangeListener listener) {
        if (view != null) {
            ((SeekBar)view).setOnSeekBarChangeListener(listener);
        }

        return this;
    }


    public SeekbarQuery value(int progress) {
        if (view != null) {
            ((SeekBar)view).setProgress(progress);
        }

        return this;
    }

    public SeekbarQuery max(int max) {
        if (view != null) {
            ((SeekBar)view).setMax(max);
        }

        return this;
    }
}
