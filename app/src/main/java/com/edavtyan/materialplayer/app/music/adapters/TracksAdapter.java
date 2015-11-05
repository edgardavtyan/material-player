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
    public TracksAdapter(Context context) {
        super(context);
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

            getService().setTracks(TracksProvider.allFromCursor(getCursor()), getAdapterPosition());
            getService().prepare();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_addToPlaylist:
                    int trackId = getCursor().getInt(TrackColumns.ID);
                    getService().getTracks().add(TracksProvider.withId(trackId, context));
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

    /*
     * Protected methods
     */

    protected String getTrackInfo() {
        return context.getResources().getString(
                R.string.pattern_track_info,
                DurationUtils.toStringUntilHours(getCursor().getInt(TrackColumns.DURATION)),
                getCursor().getString(TrackColumns.ARTIST),
                getCursor().getString(TrackColumns.ALBUM));
    }
}
