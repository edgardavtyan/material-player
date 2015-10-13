package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class TrackAdapterWithFullInfo extends TrackAdapter {
    public TrackAdapterWithFullInfo(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public String getTrackInfo() {
        return context.getResources().getString(
                R.string.track_listitem_fullInfo,
                DurationUtils.toStringUntilHours(getCursor().getInt(COLUMN_INDEX_DURATION)),
                getCursor().getString(COLUMN_INDEX_ARTIST),
                getCursor().getString(COLUMN_INDEX_ALBUM));
    }
}
