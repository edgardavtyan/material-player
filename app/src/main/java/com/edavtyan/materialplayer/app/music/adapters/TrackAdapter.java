package com.edavtyan.materialplayer.app.music.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.data.MusicTrack;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private ArrayList<MusicTrack> tracks;



    public TrackAdapter(ArrayList<MusicTrack> tracks) {
        this.tracks = tracks;
    }



    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.listitem_track, viewGroup, false);

        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder trackViewHolder, int i) {
        MusicTrack track = tracks.get(i);

        trackViewHolder.titleTextView.setText(track.getTitle());

        String durationStr = getDurationStr(track.getDuration());
        Resources res = trackViewHolder.itemView.getContext().getResources();
        String info = res.getString(R.string.listitem_track_info,
                durationStr, track.getArtist(), track.getAlbum());

        trackViewHolder.infoTextView.setText(info);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView infoTextView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_track_title);
            infoTextView = (TextView) itemView.findViewById(R.id.listitem_track_info);
        }
    }



    private String getDurationStr(long duration) {
        long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long seconds = totalSeconds % 60;
        long minutes = totalSeconds / 60;
        long hours = minutes / 60;

        if (hours == 0) {
            return String.format("%02d:%02d", minutes, seconds);
        } else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

    }
}
