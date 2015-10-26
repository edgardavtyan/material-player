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
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class PlaylistAdapter
        extends RecyclerViewCursorAdapter<PlaylistAdapter.TrackViewHolder>
        implements ServiceConnection {

    private MusicPlayerService service;
    private boolean isBound;

    public PlaylistAdapter(Context context) {
        super(context);
        context.bindService(
                new Intent(context, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

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
        vh.titleView.setText(cursor.getString(TrackColumns.TITLE));
        vh.infoView.setText(cursor.getString(TrackColumns.ALBUM));
    }

    @Override
    protected TrackViewHolder createViewHolder(View view) {
        return new TrackViewHolder(view);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView infoView;

        public TrackViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView) itemView.findViewById(R.id.title);
            infoView = (TextView) itemView.findViewById(R.id.info);

            itemView.setOnClickListener(view -> {
                if (!isBound) {
                    return;
                }

                service.setCurrentIndex(getAdapterPosition());
                service.prepare();
            });
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerService.MusicPlayerBinder)iBinder).getService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }
}
