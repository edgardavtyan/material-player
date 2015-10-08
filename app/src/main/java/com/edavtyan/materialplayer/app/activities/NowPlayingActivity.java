package com.edavtyan.materialplayer.app.activities;

import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters.AlbumAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;

public class NowPlayingActivity extends AppCompatActivity {
    public static final String EXTRA_TRACK_ALBUM_ID = "track_id";
    public static final String EXTRA_TRACK_TITLE = "track_title";
    public static final String EXTRA_TRACK_ARTIST = "track_artist";
    public static final String EXTRA_TRACK_ALBUM = "track_album";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        initArtView();
        initTitleView();
        initInfoView();
    }


    private void initInfoView() {
        String trackInfo = getTrackInfo();
        TextView infoView = (TextView) findViewById(R.id.nowplaying_info);
        infoView.setText(trackInfo);
    }

    private void initTitleView() {
        TextView titleView = (TextView) findViewById(R.id.nowplaying_title);
        titleView.setText(getIntent().getStringExtra(EXTRA_TRACK_TITLE));
    }

    private void initArtView() {
        ImageView artView = (ImageView) findViewById(R.id.nowplaying_art);
        artView.getLayoutParams().height = getScreenSize().x;

        Picasso.with(this)
                .load(new File(getArtPath()))
                .into(artView);
    }


    private Point getScreenSize() {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

    private String getTrackInfo() {
        return getResources().getString(
                    R.string.nowplaying_info_pattern,
                    getIntent().getStringExtra(EXTRA_TRACK_ARTIST),
                    getIntent().getStringExtra(EXTRA_TRACK_ALBUM));
    }

    private String getArtPath() {
        int albumId = getIntent().getIntExtra(EXTRA_TRACK_ALBUM_ID, 0);
        Cursor cursor = getContentResolver().query(
                AlbumAdapter.URI,
                AlbumAdapter.PROJECTION,
                AlbumAdapter.COLUMN_NAME_ID + "=" + albumId,
                null,
                AlbumAdapter.SORT_ORDER);
        cursor.moveToFirst();
        String artPath = cursor.getString(AlbumAdapter.COLUMN_INDEX_ART);
        cursor.close();

        return artPath;
    }
}
