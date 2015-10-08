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
import com.edavtyan.materialplayer.app.AlbumActivity;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AlbumAdapter extends RecyclerViewCursorAdapter<AlbumAdapter.AlbumViewHolder> {
    public static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    public static final String SORT_ORDER = MediaStore.Audio.Albums.ALBUM + " ASC";

    public static final String[] PROJECTION = {
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS,
            MediaStore.Audio.Albums.ALBUM_ART
    };

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 1;
    public static final int COLUMN_INDEX_ARTIST = 2;
    public static final int COLUMN_INDEX_SONGS_COUNT = 3;
    public static final int COLUMN_INDEX_ART = 4;


    public AlbumAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }


    @Override
    protected View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_album, parent, false);

        AlbumViewHolder vh = new AlbumViewHolder(view);
        view.setTag(vh);
        return view;
    }

    @Override
    protected void bindView(View view, Context context, Cursor cursor) {
        AlbumViewHolder vh = (AlbumViewHolder) view.getTag();
        vh.titleTextView.setText(cursor.getString(COLUMN_INDEX_TITLE));
        vh.infoTextView.setText(getAdditionalInfo(cursor));

        Picasso.with(context)
                .load(new File(cursor.getString(COLUMN_INDEX_ART)))
                .placeholder(R.drawable.ic_albumart_placeholder)
                .resize(100, 100)
                .into(vh.artImageView);
    }

    @Override
    protected AlbumViewHolder createViewHolder(View view, ViewGroup parent, int position) {
        return new AlbumViewHolder(view);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView infoTextView;
        ImageView artImageView;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCursor().moveToPosition(getAdapterPosition());

                    Intent i = new Intent(context, AlbumActivity.class);
                    i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, getCursor().getInt(COLUMN_INDEX_ID));
                    i.putExtra(AlbumActivity.EXTRA_ALBUM_ART, getCursor().getString(COLUMN_INDEX_ART));
                    i.putExtra(AlbumActivity.EXTRA_ALBUM_TITLE, getCursor().getString(COLUMN_INDEX_TITLE));
                    context.startActivity(i);
                }
            });

            titleTextView = (TextView) itemView.findViewById(R.id.listitem_album_title);
            infoTextView = (TextView) itemView.findViewById(R.id.listitem_album_info);
            artImageView = (ImageView) itemView.findViewById(R.id.listitem_album_art);
        }

    }


    private String getAdditionalInfo(Cursor cursor) {
        Resources res = context.getResources();

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(COLUMN_INDEX_SONGS_COUNT),
                cursor.getInt(COLUMN_INDEX_SONGS_COUNT));

        return res.getString(
                R.string.two_strings_with_bar,
                cursor.getString(COLUMN_INDEX_ARTIST),
                tracksCount);
    }
}
