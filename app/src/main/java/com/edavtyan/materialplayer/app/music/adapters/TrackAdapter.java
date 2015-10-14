package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.connections.MusicPlayerConnection;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

import java.util.ArrayList;

public abstract class TrackAdapter extends RecyclerViewCursorAdapter<TrackAdapter.TrackViewHolder> {
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
    public static final String COLUMN_NAME_ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID;

    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerConnection playerConnection;
    private MusicPlayerService playerService;

    /* ************ */
    /* Constructors */
    /* ************ */

    public TrackAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        playerConnection = new MusicPlayerConnection();
        playerService = playerConnection.getService();
        Intent serviceIntent = new Intent(context, MusicPlayerService.class);
        context.bindService(serviceIntent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    /* *************************************************************** */
    /* RecyclerViewCursorAdapter<TrackAdapter.TrackViewHolder> members */
    /* *************************************************************** */

    @Override
    protected View newView(Context context, Cursor cursor, ViewGroup parent) {
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
    protected TrackViewHolder createViewHolder(View view, ViewGroup parent, int position) {
        return new TrackViewHolder(view);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView infoTextView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_track_title);
            infoTextView = (TextView) itemView.findViewById(R.id.listitem_track_info);
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
                playerConnection.getService().setTracks(tracks);
                playerConnection.getService().setPosition(getAdapterPosition());
                playerConnection.getService().prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* *********************** */
    /* Public abstract methods */
    /* *********************** */

    public abstract String getTrackInfo();
}
