package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.AlbumActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.columns.AlbumColumns;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

import java.io.File;

public class AlbumsAdapter extends RecyclerViewCursorAdapter<AlbumsAdapter.AlbumViewHolder> {
    /* ************ */
    /* Constructors */
    /* ************ */

    public AlbumsAdapter(Context context) {
        super(context);
    }

    /* ********************************* */
    /* RecyclerViewCursorAdapter members */
    /* ********************************* */

    @Override
    protected View newView(Context context, ViewGroup parent) {
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
        vh.titleTextView.setText(cursor.getString(AlbumColumns.TITLE));
        vh.infoTextView.setText(getAdditionalInfo(cursor));

        String artPath = getCursor().getString(AlbumColumns.ART);
        File artFile = AlbumArtUtils.getArtFileFromPath(artPath);

        Glide.with(context)
                .load(artFile)
                .error(R.drawable.fallback_cover_listitem)
                .into(vh.artImageView);
    }

    @Override
    protected AlbumViewHolder createViewHolder(View view) {
        return new AlbumViewHolder(view);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView infoTextView;
        private final ImageView artImageView;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                getCursor().moveToPosition(getAdapterPosition());

                Intent i = new Intent(context, AlbumActivity.class);
                i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, getCursor().getInt(AlbumColumns.ID));
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
                cursor.getInt(AlbumColumns.ARTIST),
                cursor.getInt(AlbumColumns.SONGS_COUNT));

        return res.getString(
                R.string.two_strings_with_bar,
                cursor.getString(AlbumColumns.ARTIST),
                tracksCount);
    }
}
