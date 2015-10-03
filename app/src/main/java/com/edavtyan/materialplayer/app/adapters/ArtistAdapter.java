package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.MusicArtist;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<MusicArtist> {
    public ArtistAdapter(Context context, ArrayList<MusicArtist> artists) {
        super(context, 0, artists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicArtist artist = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.artist_listitem, parent, false);

        }

        TextView artistTitle = (TextView) convertView.findViewById(R.id.artist_title);

        artistTitle.setText(artist.getTitle());

        return convertView;
    }
}
