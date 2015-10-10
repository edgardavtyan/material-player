package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.activities.ArtistActivity;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.squareup.picasso.Picasso;

public class ArtistAdapter extends RecyclerViewCursorAdapter<ArtistAdapter.ArtistViewHolder> {
    public static final Uri URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    public static final String SORT_ORDER = MediaStore.Audio.Artists.ARTIST + " ASC";

    public static final String[] PROJECTION = {
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
    };

    public static final int COLUMN_ID = 0;
    public static final int COLUMN_ARTIST = 1;
    public static final int COLUMN_ALBUMS_COUNT = 2;
    public static final int COLUMN_TRACKS_COUNT = 3;


    public ArtistAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }


    @Override
    protected View newView(Context context, Cursor cursor, ViewGroup parent) {
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
        vh.titleTextView.setText(cursor.getString(COLUMN_ARTIST));
        vh.countsTextView.setText(getArtistCounts(cursor));
        Picasso.with(context).load(R.drawable.fallback_artist_listitem).into(vh.artImageView);
    }

    @Override
    protected ArtistViewHolder createViewHolder(View view, ViewGroup parent, int position) {
        return new ArtistViewHolder(view);
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView countsTextView;
        ImageView artImageView;

        public ArtistViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                Intent i = new Intent(itemView.getContext(), ArtistActivity.class);

                getCursor().moveToPosition(getAdapterPosition());
                i.putExtra(ArtistActivity.EXTRA_ARTIST_ID, getCursor().getInt(COLUMN_ID));
                i.putExtra(ArtistActivity.EXTRA_ARTIST_NAME, getCursor().getString(COLUMN_ARTIST));

                itemView.getContext().startActivity(i);
            });

            titleTextView = (TextView) itemView.findViewById(R.id.listitem_artist_title);
            countsTextView = (TextView) itemView.findViewById(R.id.listitem_artist_counts);
            artImageView = (ImageView) itemView.findViewById(R.id.listitem_artist_art);
        }
    }


    private String getArtistCounts(Cursor cursor) {
        Resources res = context.getResources();

        String albumsCount = res.getQuantityString(
                R.plurals.albums,
                cursor.getInt(COLUMN_ALBUMS_COUNT),
                cursor.getInt(COLUMN_ALBUMS_COUNT));

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(COLUMN_TRACKS_COUNT),
                cursor.getInt(COLUMN_TRACKS_COUNT));

        return res.getString(R.string.two_strings_with_bar, albumsCount, tracksCount);
    }
}
