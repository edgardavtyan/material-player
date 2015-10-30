package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewServiceAdapter;

public class PlaylistAdapter
        extends RecyclerViewServiceAdapter<PlaylistAdapter.TrackViewHolder> {

    private final Context context;

    public PlaylistAdapter(Context context) {
        super(context);
        this.context = context;
    }

    /*
     * ViewHolder
     */

    public class TrackViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private final TextView titleView;
        private final TextView infoView;
        private final ImageButton menuButton;

        public TrackViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleView = (TextView) itemView.findViewById(R.id.title);
            infoView = (TextView) itemView.findViewById(R.id.info);
            menuButton = (ImageButton) itemView.findViewById(R.id.menu);

            PopupMenu popupMenu = new PopupMenu(context, menuButton);
            popupMenu.inflate(R.menu.menu_queue);
            popupMenu.setOnMenuItemClickListener(this);
            menuButton.setOnClickListener(view -> popupMenu.show());
        }

        @Override
        public void onClick(View view) {
            if (!isBound()) {
                return;
            }

            getService().setCurrentIndex(getAdapterPosition());
            getService().prepare();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_remove:
                    getService().getTracks().remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;

                default:
                    return false;
            }
        }
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
}