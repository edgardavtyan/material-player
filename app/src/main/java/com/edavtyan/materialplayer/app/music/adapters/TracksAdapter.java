package com.edavtyan.materialplayer.app.music.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

import java.util.ArrayList;

public class TracksAdapter extends RecyclerViewCursorAdapter<TracksAdapter.TrackViewHolder> {
    /* ********* */
    /* Constants */
    /* ********* */

    public static final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final String SORT_ORDER = MediaStore.Audio.Media.TITLE + " ASC";
    public static final String[] PROJECTION = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID
    };

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 2;
    public static final int COLUMN_INDEX_ARTIST = 3;
    public static final int COLUMN_INDEX_ALBUM = 4;
    public static final int COLUMN_INDEX_DURATION = 5;

    public static final String COLUMN_NAME_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;

    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerService playerService;
    private final MusicPlayerConnection playerConnection;

    /* ************ */
    /* Constructors */
    /* ************ */

    public TracksAdapter(Context context) {
        super(context);
        playerConnection = new MusicPlayerConnection();
        Intent serviceIntent = new Intent(context, MusicPlayerService.class);
        context.bindService(serviceIntent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    /* ******* */
    /* Classes */
    /* ******* */

    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerBinder binder = (MusicPlayerBinder) iBinder;
            playerService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    }

    /* *************************************************************** */
    /* RecyclerViewCursorAdapter<TracksAdapter.TrackViewHolder> members */
    /* *************************************************************** */

    @Override
    protected View newView(Context context, ViewGroup parent) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_track, parent, false);

        TrackViewHolder vh = new TrackViewHolder(view);
        view.setTag(vh);
        return view;
    }

    @Override
    protected void bindView(View view, Context context, Cursor cursor) {
        TrackViewHolder vh = (TrackViewHolder) view.getTag();
        vh.titleTextView.setText(cursor.getString(COLUMN_INDEX_TITLE));
        vh.infoTextView.setText(getTrackInfo());
    }

    @Override
    protected TrackViewHolder createViewHolder(View view) {
        return new TrackViewHolder(view);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView titleTextView;
        final TextView infoTextView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
        }

        @Override
        public void onClick(View view){
            getCursor().moveToPosition(getAdapterPosition());
            Intent i = new Intent(context, NowPlayingActivity.class);
            context.startActivity(i);

            ArrayList<Integer> tracks = new ArrayList<>();
            getCursor().moveToFirst();
            do {
                tracks.add(getCursor().getInt(COLUMN_INDEX_ID));
            } while (getCursor().moveToNext());

            try {
                playerService.setTracks(tracks);
                playerService.setCurrentIndex(getAdapterPosition());
                playerService.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public String getTrackInfo() {
        return context.getResources().getString(
                R.string.track_listitem_fullInfo,
                DurationUtils.toStringUntilHours(getCursor().getInt(COLUMN_INDEX_DURATION)),
                getCursor().getString(COLUMN_INDEX_ARTIST),
                getCursor().getString(COLUMN_INDEX_ALBUM));
    }

    public void closeConnection() {
        context.unbindService(playerConnection);
    }
}
