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

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.AlbumActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

import java.io.File;

public class AlbumsAdapter extends RecyclerViewCursorAdapter<AlbumsAdapter.AlbumViewHolder> {
    /* ********* */
    /* Constants */
    /* ********* */

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

    /* ************ */
    /* Constructors */
    /* ************ */

    public AlbumsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    /* ********************************* */
    /* RecyclerViewCursorAdapter members */
    /* ********************************* */

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

        String artPath = getCursor().getString(COLUMN_INDEX_ART);
        File artFile = AlbumArtUtils.getArtFileFromPath(artPath);
        AlbumArtUtils.getSmallArtRequest(context, artFile).into(vh.artImageView);
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

            itemView.setOnClickListener(view -> {
                getCursor().moveToPosition(getAdapterPosition());

                Intent i = new Intent(context, AlbumActivity.class);
                i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, getCursor().getInt(COLUMN_INDEX_ID));
                context.startActivity(i);
            });

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
            artImageView = (ImageView) itemView.findViewById(R.id.art);
        }

    }

    /* *************** */
    /* Private methods */
    /* *************** */

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
