package com.edavtyan.materialplayer.app.music.adapters2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.ArtistActivity;
import com.edavtyan.materialplayer.app.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
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



    private CursorAdapter cursorAdapter;

    private Context context;



    public ArtistAdapter(Context context, Cursor cursor) {
        this.context = context;
        createCursorAdapter(cursor);
    }



    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return new ArtistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView countsTextView;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_artist_title);
            countsTextView = (TextView) itemView.findViewById(R.id.listitem_artist_counts);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(itemView.getContext(), ArtistActivity.class);

            cursorAdapter.getCursor().moveToPosition(getAdapterPosition());
            String artistTitle = cursorAdapter.getCursor().getString(COLUMN_ARTIST);
            i.putExtra(ArtistActivity.EXTRA_ARTIST_TITLE, artistTitle);

            itemView.getContext().startActivity(i);
        }
    }



    private void createCursorAdapter(Cursor cursor) {
        cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater
                        .from(context)
                        .inflate(R.layout.listitem_artist, parent, false);

                ArtistViewHolder vh = new ArtistViewHolder(view);
                view.setTag(vh);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ArtistViewHolder vh = (ArtistViewHolder) view.getTag();
                vh.titleTextView.setText(cursor.getString(COLUMN_ARTIST));
                vh.countsTextView.setText(getArtistCounts(cursor));
            }
        };
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
