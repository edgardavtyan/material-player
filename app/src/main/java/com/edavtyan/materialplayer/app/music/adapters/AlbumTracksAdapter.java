package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;

import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class AlbumTracksAdapter extends TracksAdapter {
    public AlbumTracksAdapter(Context context) {
        super(context);
    }

    @Override
    public String getTrackInfo() {
        return DurationUtils.toStringUntilHours(getCursor().getInt(TrackColumns.DURATION));
    }
}
