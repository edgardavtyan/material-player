package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewServiceAdapter;

public class PlaylistAdapter
        extends RecyclerViewServiceAdapter<PlaylistAdapter.TrackViewHolder> {

    private Context context;

    public PlaylistAdapter(Context context) {
        super(context);
        this.context = context;
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
        if (!isBound()) return;
        holder.titleView.setText(getService().getTracks().get(position).getTrackTitle());
        holder.infoView.setText(getService().getTracks().get(position).getAlbumTitle());
    }

    @Override
    public int getItemCount() {
        if (!isBound()) return 0;
        return getService().getTracks().size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView infoView;

        public TrackViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView) itemView.findViewById(R.id.title);
            infoView = (TextView) itemView.findViewById(R.id.info);

            itemView.setOnClickListener(view -> {
                if (!isBound()) {
                    return;
                }

                getService().setCurrentIndex(getAdapterPosition());
                getService().prepare();
            });
        }
    }
}
