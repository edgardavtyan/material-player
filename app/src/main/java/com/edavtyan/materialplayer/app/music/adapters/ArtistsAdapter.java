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
    public ArtistsAdapter(Context context) {
        super(context);
    }

    /*
     * RecyclerViewCursorAdapter
     */

    @Override
    protected View newView(Context context, ViewGroup parent) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_artist, parent, false);

        ArtistViewHolder vh = new ArtistViewHolder(view);
        view.setTag(vh);
        return view;
    }

    @Override
    protected void bindView(View view, Context context, Cursor cursor) {
        ArtistViewHolder vh = (ArtistViewHolder) view.getTag();
        vh.titleTextView.setText(cursor.getString(ArtistColumns.TITLE));
        vh.countsTextView.setText(getArtistCounts(cursor));
    }

    @Override
    protected ArtistViewHolder createViewHolder(View view) {
        return new ArtistViewHolder(view);
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView countsTextView;

        public ArtistViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                Intent i = new Intent(itemView.getContext(), ArtistActivity.class);

                getCursor().moveToPosition(getAdapterPosition());
                i.putExtra(
                        ArtistActivity.EXTRA_ARTIST_NAME,
                        getCursor().getString(ArtistColumns.TITLE));

                itemView.getContext().startActivity(i);
            });

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            countsTextView = (TextView) itemView.findViewById(R.id.info);
        }
    }

    /*
     * Private methods
     */

    private String getArtistCounts(Cursor cursor) {
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
