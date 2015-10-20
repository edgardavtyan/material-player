package com.edavtyan.materialplayer.app.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.squareup.picasso.Picasso;

public class FloatingNowPlayingFragment extends Fragment implements View.OnClickListener {
    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerConnection playerConnection;
    private MusicPlayerService playerService;
    private boolean isBound;

    private ImageView artView;
    private TextView titleView;
    private ImageButton controlView;
    private LinearLayout container;

    /* ******* */
    /* Classes */
    /* ******* */

    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerBinder binder = (MusicPlayerBinder) iBinder;
            playerService = binder.getService();
            isBound = true;

            if (playerService.hasData()) {
                container.setVisibility(View.VISIBLE);
                syncDataWithService();
            } else {
                container.setVisibility(View.GONE);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    }

    /* **************** */
    /* Fragment members */
    /* **************** */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_floating_nowplaying, parent, false);

        artView = (ImageView) view.findViewById(R.id.art);
        artView.setOnClickListener(this);

        titleView = (TextView) view.findViewById(R.id.title);
        titleView.setOnClickListener(this);

        controlView = (ImageButton) view.findViewById(R.id.play_pause);
        controlView.setOnClickListener(this);

        container = (LinearLayout) view.findViewById(R.id.container);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        playerConnection = new MusicPlayerConnection();
        Intent intent = new Intent(getContext(), MusicPlayerService.class);
        getContext().bindService(intent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        getContext().unbindService(playerConnection);

        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (isBound && playerService.hasData()) {
            container.setVisibility(View.VISIBLE);
            syncDataWithService();
        } else {
            container.setVisibility(View.GONE);
        }
    }

    /* ******************** */
    /* View.OnClickListener */
    /* ******************** */

    // TODO: fix this mess
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title:
            case R.id.art:
                Intent intent = new Intent(getContext(), NowPlayingActivity.class);
                startActivity(intent);
                break;

            case R.id.play_pause:
                if (playerService.isPlaying()) {
                    playerService.pause();
                    controlView.setImageResource(R.drawable.ic_play_white);
                } else {
                    playerService.resume();
                    controlView.setImageResource(R.drawable.ic_pause_white);
                }
                break;
        }
    }

    /* *************** */
    /* Private methods */
    /* *************** */

    private void syncDataWithService() {
        Picasso.with(getContext())
                .load(playerService.getMetadata().getArtFile())
                .placeholder(R.drawable.fallback_cover)
                .error(R.drawable.fallback_cover)
                .into(artView);

        titleView.setText(playerService.getMetadata().getTrackTitle());

        if (playerService.isPlaying()) {
            controlView.setImageResource(R.drawable.ic_pause_white);
        } else {
            controlView.setImageResource(R.drawable.ic_play_white);
        }
    }
}
