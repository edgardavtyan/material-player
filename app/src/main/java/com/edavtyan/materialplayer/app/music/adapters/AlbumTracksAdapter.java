package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class AlbumTracksAdapter extends TracksAdapter {
    public static final String SORT_ORDER = MediaStore.Audio.Media.TRACK + " ASC";

    public AlbumTracksAdapter(Context context) {
        super(context);
    }

    @Override
    public String getTrackInfo() {
        return DurationUtils.toStringUntilHours(getCursor().getInt(COLUMN_INDEX_DURATION));
    }
}
