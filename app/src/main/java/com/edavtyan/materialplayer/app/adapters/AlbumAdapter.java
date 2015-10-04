package com.edavtyan.materialplayer.app.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.data.MusicAlbum;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private ArrayList<MusicAlbum> albums;


    public AlbumAdapter(ArrayList<MusicAlbum> albums) {
        this.albums = albums;
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.listitem_album, viewGroup, false);

        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        MusicAlbum album = albums.get(i);

        albumViewHolder.titleTextView.setText(album.getTitle());

        Resources res = albumViewHolder.itemView.getContext().getResources();
        String tracksCount = res.getQuantityString(R.plurals.tracks,
                album.getTracksCount(), album.getTracksCount());
        String albumInfo = res.getString(R.string.two_strings_with_bar, album.getArtist(), tracksCount);
        albumViewHolder.countsTextView.setText(albumInfo);


        Picasso.with(albumViewHolder.itemView.getContext())
                .load(new File(albums.get(i).getArt()))
                .resize(100, 100)
                .into(albumViewHolder.artImageView);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView countsTextView;
        ImageView artImageView;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_album_title);
            countsTextView = (TextView) itemView.findViewById(R.id.listitem_album_counts);
            artImageView = (ImageView) itemView.findViewById(R.id.listitem_album_art);
        }
    }
}
