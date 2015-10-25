package com.edavtyan.materialplayer.app.music.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.edavtyan.materialplayer.app.music.providers.TracksProvider;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class TracksAdapter extends RecyclerViewCursorAdapter<TracksAdapter.TrackViewHolder> {
    private MusicPlayerService playerService;
    private final MusicPlayerConnection playerConnection;

    public TracksAdapter(Context context) {
        super(context);
        playerConnection = new MusicPlayerConnection();
        Intent serviceIntent = new Intent(context, MusicPlayerService.class);
        context.bindService(serviceIntent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerBinder binder = (MusicPlayerBinder) iBinder;
            playerService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    }

    /*
     * RecyclerViewCursorAdapter<TracksAdapter.TrackViewHolder>
     */

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
        vh.titleTextView.setText(cursor.getString(TrackColumns.TITLE));
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
            Intent i = new Intent(context, NowPlayingActivity.class);
            context.startActivity(i);

            playerService.setTracks(TracksProvider.getAllIds(getCursor()));
            playerService.setCurrentIndex(getAdapterPosition());
            playerService.prepare();
        }
    }

    /*
     * Public methods
     */

    public String getTrackInfo() {
        return context.getResources().getString(
                R.string.track_listitem_fullInfo,
                DurationUtils.toStringUntilHours(getCursor().getInt(TrackColumns.DURATION)),
                getCursor().getString(TrackColumns.ARTIST),
                getCursor().getString(TrackColumns.ALBUM));
    }

    public void closeConnection() {
        context.unbindService(playerConnection);
    }
}
