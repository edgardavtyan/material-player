package com.edavtyan.materialplayer.app.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.data.MusicArtist;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private ArrayList<MusicArtist> artists;


    public ArtistAdapter(ArrayList<MusicArtist> artists) {
        this.artists = artists;
    }


    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.listitem_artist, viewGroup, false);

        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder artistViewHolder, int i) {
        MusicArtist artist = artists.get(i);
        artistViewHolder.titleTextView.setText(artist.getTitle());

        Resources res = artistViewHolder.itemView.getContext().getResources();

        String albumsCount = res.getQuantityString(R.plurals.albums,
                artist.getAlbumsCount(), artist.getAlbumsCount());
        String tracksCount = res.getQuantityString(R.plurals.tracks,
                artist.getTracksCount(), artist.getTracksCount());
        String counts = res.getString(R.string.two_strings_with_bar, albumsCount, tracksCount);
        artistViewHolder.countsTextView.setText(counts);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView countsTextView;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_artist_title);
            countsTextView = (TextView) itemView.findViewById(R.id.listitem_artist_counts);
        }
    }
}
