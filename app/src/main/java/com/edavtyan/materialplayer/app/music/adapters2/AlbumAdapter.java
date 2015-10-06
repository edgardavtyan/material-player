package com.edavtyan.materialplayer.app.music.adapters2;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    // Constants
    // ============================================================================================

    public static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    public static final String SORT_ORDER = MediaStore.Audio.Albums.ALBUM + " ASC";

    public static final String[] PROJECTION = {
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS,
            MediaStore.Audio.Albums.ALBUM_ART
    };

    public static final int COLUMN_ID = 0;
    public static final int COLUMN_TITLE = 1;
    public static final int COLUMN_ARTIST = 2;
    public static final int COLUMN_SONGS_COUNT = 3;
    public static final int COLUMN_ART = 4;

    // ============================================================================================
    // ============================================================================================



    // Fields
    // ============================================================================================

    private CursorAdapter cursorAdapter;

    private Context context;

    // ============================================================================================
    // ============================================================================================



    // Constructors
    // ============================================================================================

    public AlbumAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater
                        .from(context)
                        .inflate(R.layout.listitem_album, parent, false);

                AlbumViewHolder vh = new AlbumViewHolder(view);
                view.setTag(vh);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                AlbumViewHolder vh = (AlbumViewHolder) view.getTag();
                vh.titleTextView.setText(cursor.getString(COLUMN_TITLE));
                vh.infoTextView.setText(getAdditionalInfo(cursor));

                Picasso.with(context)
                        .load(new File(cursor.getString(COLUMN_ART)))
                        .placeholder(R.drawable.ic_albumart_placeholder)
                        .resize(100, 100)
                        .into(vh.artImageView);
            }
        };
    }

    // ============================================================================================
    // ============================================================================================



    // RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> implementation
    // ============================================================================================

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView infoTextView;
        ImageView artImageView;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_album_title);
            infoTextView = (TextView) itemView.findViewById(R.id.listitem_album_info);
            artImageView = (ImageView) itemView.findViewById(R.id.listitem_album_art);
        }

    }

    // ============================================================================================
    // ============================================================================================



    // Private methods
    // ============================================================================================

    private String getAdditionalInfo(Cursor cursor) {
        Resources res = context.getResources();

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(COLUMN_SONGS_COUNT),
                cursor.getInt(COLUMN_SONGS_COUNT));

        return res.getString(
                R.string.two_strings_with_bar,
                cursor.getString(COLUMN_ARTIST),
                tracksCount);
    }

    // ============================================================================================
    // ============================================================================================
}
