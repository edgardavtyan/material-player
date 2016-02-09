package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerServiceCursorAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.edavtyan.materialplayer.app.music.providers.TracksProvider;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class TracksAdapter
        extends RecyclerServiceCursorAdapter<TracksAdapter.TrackViewHolder> {
    public TracksAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    /*
     * ViewHolder
     */

    public class TrackViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private final TextView titleTextView;
        private final TextView infoTextView;
        private final ImageButton menuButton;

        public TrackViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
            menuButton = (ImageButton) itemView.findViewById(R.id.menu);

            PopupMenu popupMenu = new PopupMenu(context, menuButton);
            popupMenu.inflate(R.menu.menu_track);
            popupMenu.setOnMenuItemClickListener(this);
            menuButton.setOnClickListener(view -> popupMenu.show());
        }

        @Override
        public void onClick(View view){
            Intent i = new Intent(context, NowPlayingActivity.class);
            context.startActivity(i);

            getService().getPlayer().setTracks(TracksProvider.allFromCursor(cursor), getAdapterPosition());
            getService().getPlayer().prepare();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_addToPlaylist:
                    cursor.moveToPosition(getAdapterPosition());
                    int trackId = cursor.getInt(TrackColumns.ID);
                    getService().getPlayer().getQueue().add(TracksProvider.withId(trackId, context));
                    return true;

                default:
                    return false;
            }
        }
    }

    /*
     * RecyclerViewCursorAdapter<TracksAdapter.TrackViewHolder>
     */

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.titleTextView.setText(cursor.getString(TrackColumns.TITLE));
        holder.infoTextView.setText(getTrackInfo());
    }

    /*
     * Protected methods
     */

    protected String getTrackInfo() {
        return context.getResources().getString(
                R.string.pattern_track_info,
                DurationUtils.toStringUntilHours(cursor.getInt(TrackColumns.DURATION)),
                cursor.getString(TrackColumns.ARTIST),
                cursor.getString(TrackColumns.ALBUM));
    }
}
