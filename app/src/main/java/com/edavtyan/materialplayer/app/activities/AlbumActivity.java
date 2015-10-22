package com.edavtyan.materialplayer.app.activities;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ImageView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.CollapsingHeaderListActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.Album;
import com.edavtyan.materialplayer.app.music.ArtProvider;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.music.adapters.AlbumTracksAdapter;
import com.edavtyan.materialplayer.app.music.adapters.TracksAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AlbumActivity extends CollapsingHeaderListActivity {

    public static final String EXTRA_ALBUM_ID = "album_id";

    private TracksAdapter tracksAdapter;
    private Album album;


    private class ImageLoadTask extends AsyncTask<Integer, Void, File> {
        @Override
        protected File doInBackground(Integer... albumIds) {
            Metadata metadata = Metadata.firstTrackOfAlbum(albumIds[0], AlbumActivity.this);
            return new ArtProvider().getArt(metadata);
        }

        @Override
        protected void onPostExecute(File artFile) {
            ImageView artView = (ImageView) findViewById(R.id.art);
            Picasso.with(AlbumActivity.this)
                    .load(artFile)
                    .placeholder(R.drawable.fallback_cover)
                    .error(R.drawable.fallback_cover)
                    .into(artView);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        tracksAdapter = new AlbumTracksAdapter(this);
        album = Album.fromId(getIntent().getIntExtra(EXTRA_ALBUM_ID, -1), this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        tracksAdapter.closeConnection();
        super.onDestroy();
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

    @Override
    public String getToolbarTitle() {
        return album.getTitle();
    }

    @Override
    public void setImageSource(ImageView imageView) {
        new ImageLoadTask().execute(album.getId());
    }
}
