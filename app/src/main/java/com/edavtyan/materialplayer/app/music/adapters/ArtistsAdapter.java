package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.ArtistActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.columns.ArtistColumns;

public class ArtistsAdapter extends RecyclerViewCursorAdapter<ArtistsAdapter.ArtistViewHolder> {
    public ArtistsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    /*
     * ViewHolder
     */

    public class ArtistViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView titleTextView;
        private final TextView countsTextView;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            countsTextView = (TextView) itemView.findViewById(R.id.info);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(itemView.getContext(), ArtistActivity.class);

            cursor.moveToPosition(getAdapterPosition());
            i.putExtra(
                    ArtistActivity.EXTRA_ARTIST_NAME,
                    cursor.getString(ArtistColumns.TITLE));

            itemView.getContext().startActivity(i);
        }
    }


    /*
     * RecyclerViewCursorAdapter
     */

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.titleTextView.setText(cursor.getString(ArtistColumns.TITLE));
        holder.countsTextView.setText(getArtistInfo(cursor));
    }

    /*
     * Private methods
     */

    private String getArtistInfo(Cursor cursor) {
        Resources res = context.getResources();

        String albumsCount = res.getQuantityString(
                R.plurals.albums,
                cursor.getInt(ArtistColumns.ALBUMS_COUNT),
                cursor.getInt(ArtistColumns.ALBUMS_COUNT));

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(ArtistColumns.TRACKS_COUNT),
                cursor.getInt(ArtistColumns.TRACKS_COUNT));

        return res.getString(R.string.pattern_artist_info, albumsCount, tracksCount);
    }
}
