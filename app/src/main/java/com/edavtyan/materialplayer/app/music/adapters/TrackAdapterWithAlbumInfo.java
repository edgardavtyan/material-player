package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class TrackAdapterWithAlbumInfo extends TrackAdapter {
    public TrackAdapterWithAlbumInfo(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public String getTrackInfo() {
        return context.getResources().getString(
                R.string.track_listitem_timeAlbumInfo,
                DurationUtils.toStringUntilHours(getCursor().getInt(COLUMN_INDEX_DURATION)),
                getCursor().getString(COLUMN_INDEX_ALBUM));
    }
}
