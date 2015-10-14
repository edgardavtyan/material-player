package com.edavtyan.materialplayer.app.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

public class FloatingNowPlayingFragment extends Fragment implements View.OnClickListener {
    /* ********* */
    /* Constants */
    /* ********* */

    private final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private final String[] PROJECTION = new String[] {
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM_ID
    };
    private final int COLUMN_TITLE = 0;
    private final int COLUMN_ALBUM_ID = 1;

    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerService playerService;
    private boolean isBound;

    private ImageView artView;
    private TextView titleView;
    private ImageButton controlView;
    private LinearLayout container;

    /* ******* */
    /* Classes */
    /* ******* */

    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerBinder binder = (MusicPlayerBinder) iBinder;
            playerService = binder.getService();
            isBound = true;

            if (playerService.hasData()) {
                container.setVisibility(View.VISIBLE);
                syncDataWithService();
            } else {
                container.setVisibility(View.GONE);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    }

    /* **************** */
    /* Fragment members */
    /* **************** */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_floating_nowplaying, parent, false);

        artView = (ImageView) view.findViewById(R.id.nowplaying_floating_art);
        artView.setOnClickListener(this);

        titleView = (TextView) view.findViewById(R.id.nowplaying_floating_title);
        titleView.setOnClickListener(this);

        controlView = (ImageButton) view.findViewById(R.id.nowplaying_floating_control);
        controlView.setOnClickListener(this);

        container = (LinearLayout) view.findViewById(R.id.nowplaying_floating_container);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MusicPlayerConnection playerConnection = new MusicPlayerConnection();
        Intent intent = new Intent(getContext(), MusicPlayerService.class);
        getContext().bindService(intent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (isBound && playerService.hasData()) {
            container.setVisibility(View.VISIBLE);
            syncDataWithService();
        } else {
            container.setVisibility(View.GONE);
        }
    }

    /* ******************** */
    /* View.OnClickListener */
    /* ******************** */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nowplaying_floating_title:
                // falls through
            case R.id.nowplaying_art:
                Cursor cursor = null;
                int albumId = -1;
                try {
                    int trackId = playerService.getCurrentTrackId();
                    cursor = getContext().getContentResolver().query(
                            URI, PROJECTION,
                            MediaStore.Audio.Media._ID + "=" + trackId,
                            null, null);
                    cursor.moveToFirst();
                    albumId = cursor.getInt(COLUMN_ALBUM_ID);
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }

                Intent intent = new Intent(getContext(), NowPlayingActivity.class);
                intent.putExtra(NowPlayingActivity.EXTRA_TRACK_ALBUM_ID, albumId);
                startActivity(intent);
                break;

            case R.id.nowplaying_floating_control:
                if (playerService.isPlaying()) {
                    playerService.pause();
                    controlView.setImageResource(R.drawable.ic_play_white_36dp);
                } else {
                    playerService.resume();
                    controlView.setImageResource(R.drawable.ic_pause_white_36dp);
                }
        }
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public void playPause(View view) {
        if (playerService.isPlaying()) {
            playerService.pause();
            controlView.setImageResource(R.drawable.ic_play_white_36dp);
        } else {
            playerService.resume();
            controlView.setImageResource(R.drawable.ic_pause_white_36dp);
        }
    }

    public void launchNowPlayingActivity(View view) {
        Cursor cursor = null;
        int albumId = -1;
        try {
            int trackId = playerService.getCurrentTrackId();
            cursor = getContext().getContentResolver().query(
                    URI, PROJECTION,
                    MediaStore.Audio.Media._ID + "=" + trackId,
                    null, null);
            cursor.moveToFirst();
            albumId = cursor.getInt(COLUMN_ALBUM_ID);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Intent intent = new Intent(getContext(), NowPlayingActivity.class);
        intent.putExtra(NowPlayingActivity.EXTRA_TRACK_ALBUM_ID, albumId);
        startActivity(intent);
    }

    /* *************** */
    /* Private methods */
    /* *************** */

    private void syncDataWithService() {
        int trackId = playerService.getCurrentTrackId();
        Cursor cursor = null;
        try {
            cursor = getContext().getContentResolver().query(
                    URI, PROJECTION,
                    MediaStore.Audio.Media._ID + "=" + trackId,
                    null, null);
            cursor.moveToFirst();

            titleView.setText(cursor.getString(COLUMN_TITLE));

            int albumId = cursor.getInt(COLUMN_ALBUM_ID);
            File art = AlbumArtUtils.getArtFileFromId(albumId, getContext());
            Picasso.with(getContext())
                    .load(art)
                    .error(R.drawable.fallback_cover)
                    .placeholder(R.drawable.fallback_cover)
                    .fit()
                    .into(artView);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // Sync control
        if (playerService.isPlaying()) {
            controlView.setImageResource(R.drawable.ic_pause_white_36dp);
        } else {
            controlView.setImageResource(R.drawable.ic_play_white_36dp);
        }
    }
}
