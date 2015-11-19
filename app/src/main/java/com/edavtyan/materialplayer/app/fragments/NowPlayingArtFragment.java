package com.edavtyan.materialplayer.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.fragments.base.ServiceFragment;
import com.edavtyan.materialplayer.app.music.providers.ArtProvider;
import com.edavtyan.materialplayer.app.music.data.Track;
import com.edavtyan.materialplayer.app.resources.AppColors;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;

import java.io.File;

public class NowPlayingArtFragment extends ServiceFragment {
    private ImageView artView;
    private ImageView backView;

    /*
     * BroadcastReceivers
     */

    private final BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncWithService();
        }
    };

    /*
     * AsyncTasks
     */

    private class ArtLoadTask extends AsyncTask<Track, Void, DrawableRequestBuilder> {
        @Override
        protected DrawableRequestBuilder doInBackground(Track... tracks) {
            File artFile = ArtProvider.fromTrack(tracks[0]);
            return Glide.with(getActivity())
                    .load(artFile)
                    .error(R.drawable.fallback_cover);
        }

        @Override
        protected void onPostExecute(DrawableRequestBuilder artRequest) {
            artRequest.into(artView);
            artRequest.into(backView);
        }
    }

    /*
     * ServiceFragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nowplaying_art, container, false);

        artView = (ImageView) view.findViewById(R.id.art);
        artView.setColorFilter(AppColors.getTextSecondary(getContext()));
        backView = (ImageView) view.findViewById(R.id.back);
        backView.setColorFilter(AppColors.getTextSecondary(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(newTrackReceiver);
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        syncWithService();
    }

    /*
     * Private methods
     */

    private void syncWithService() {
        new ArtLoadTask().execute(getService().getPlayer().getCurrentTrack());
    }
}
