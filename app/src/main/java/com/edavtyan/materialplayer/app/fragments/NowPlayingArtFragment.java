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
import com.edavtyan.materialplayer.app.music.ArtProvider;
import com.edavtyan.materialplayer.app.music.Metadata;
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

    private class ArtLoadTask extends AsyncTask<Metadata, Void, File> {
        @Override
        protected File doInBackground(Metadata... metadata) {
            return ArtProvider.fromMetadata(metadata[0]);
        }

        @Override
        protected void onPostExecute(File file) {
            if (file.exists()) {
                DrawableRequestBuilder artRequest = Glide
                        .with(getActivity())
                        .load(file)
                        .error(R.drawable.fallback_cover);
                artRequest.into(artView);
                artRequest.into(backView);
            }
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
        backView = (ImageView) view.findViewById(R.id.back);

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
        new ArtLoadTask().execute(getService().getCurrentTrack());
    }
}
