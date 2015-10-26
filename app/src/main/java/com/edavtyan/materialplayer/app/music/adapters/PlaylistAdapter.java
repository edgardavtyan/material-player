package com.edavtyan.materialplayer.app.music.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

public class PlaylistAdapter
        extends RecyclerView.Adapter<PlaylistAdapter.TrackViewHolder>
        implements ServiceConnection {

    private Context context;
    private MusicPlayerService service;
    private boolean isBound;

    public PlaylistAdapter(Context context) {
        this.context = context;
        context.bindService(
                new Intent(context, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_track, parent, false);

        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        if (!isBound) return;
        holder.titleView.setText(service.getTracks().get(position).getTrackTitle());
        holder.infoView.setText(service.getTracks().get(position).getAlbumTitle());
    }

    @Override
    public int getItemCount() {
        if (!isBound) return 0;
        return service.getTracks().size();
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
