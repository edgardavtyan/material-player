package com.edavtyan.materialplayer.app.activities;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.CollapsingHeaderListActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.Album;
import com.edavtyan.materialplayer.app.music.ArtProvider;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.music.adapters.AlbumTracksAdapter;
import com.edavtyan.materialplayer.app.music.adapters.TracksAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.io.File;

public class AlbumActivity extends CollapsingHeaderListActivity {
    public static final String EXTRA_ALBUM_ID = "album_id";

    private TracksAdapter tracksAdapter;

    /*
     * AsyncTasks
     */

    private class ImageLoadTask extends AsyncTask<Integer, Void, File> {
        @Override
        protected File doInBackground(Integer... albumIds) {
            Metadata metadata = Metadata.firstTrackOfAlbum(albumIds[0], AlbumActivity.this);
            return new ArtProvider().getArt(metadata);
        }

        @Override
        protected void onPostExecute(File artFile) {
            ImageView artView = (ImageView) findViewById(R.id.art);
            Glide.with(AlbumActivity.this)
                    .load(artFile)
                    .error(R.drawable.fallback_cover)
                    .into(artView);
        }
    }

    /*
     * CollapsingHeaderListActivity
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        tracksAdapter = new AlbumTracksAdapter(this);
        super.onCreate(savedInstanceState);

        Album album = Album.fromId(getIntent().getIntExtra(EXTRA_ALBUM_ID, -1), this);

        titleView.setText(album.getTitle());
        String tracksCount = getResources().getQuantityString(
                R.plurals.tracks, album.getTracksCount(), album.getTracksCount());
        String albumInfo = getResources().getString(
                R.string.pattern_album_info, album.getArtistTitle(), tracksCount);
        infoView.setText(albumInfo);

        new ImageLoadTask().execute(album.getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tracksAdapter.bindService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        tracksAdapter.unbindService();
    }

    @Override
    public Loader<Cursor> getLoader() {
        int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, 0);
        return new CursorLoader(
                this,
                TrackColumns.URI,
                TrackColumns.PROJECTION,
                TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                null,
                TrackColumns.NAME_TRACK + " ASC");
    }

    @Override
    public RecyclerViewCursorAdapter getAdapter() {
        return tracksAdapter;
    }
}
