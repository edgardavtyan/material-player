package com.edavtyan.materialplayer.app;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapter;

public class AlbumActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String EXTRA_ALBUM_TITLE = "album_title";
    public static final String EXTRA_ALBUM_ART = "album_art";
    public static final String EXTRA_ALBUM_ID = "album_id";

    public static final int LOADER_ID = 2;



    private TrackAdapter trackAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getLoaderManager().initLoader(LOADER_ID, savedInstanceState, this);
        trackAdapter = new TrackAdapter(this, null);

        initRecyclerView();
    }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new TracksLoader(this, getIntent().getIntExtra(EXTRA_ALBUM_ID, 0));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        trackAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        trackAdapter.swapCursor(null);
    }

    private static class TracksLoader extends CursorLoader {
        private final int albumId;

        public TracksLoader(Context context, int albumId) {
            super(context);
            this.albumId = albumId;
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    TrackAdapter.URI,
                    TrackAdapter.PROJECTION,
                    MediaStore.Audio.Media.ALBUM_ID + "=" + albumId,
                    null,
                    TrackAdapter.SORT_ORDER);
        }
    }



    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.album_tracks_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trackAdapter);
    }
}
