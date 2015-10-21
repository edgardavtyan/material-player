package com.edavtyan.materialplayer.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.fragments.base.ServiceFragment;
import com.squareup.picasso.Picasso;

public class FloatingNowPlayingFragment
        extends ServiceFragment
        implements View.OnClickListener {
    /* ****** */
    /* Fields */
    /* ****** */

    private ImageView artView;
    private TextView titleView;
    private TextView infoView;
    private ImageButton controlView;
    private LinearLayout container;

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

        infoView = (TextView) view.findViewById(R.id.info);

        controlView = (ImageButton) view.findViewById(R.id.play_pause);
        controlView.setOnClickListener(this);

        container = (LinearLayout) view.findViewById(R.id.container);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (isBound() && getService().hasData()) {
            container.setVisibility(View.VISIBLE);
            syncDataWithService();
        } else {
            container.setVisibility(View.GONE);
        }
    }

    @Override
    public void onServiceConnected() {
        if (getService().hasData()) {
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
                if (getService().isPlaying()) {
                    getService().pause();
                    controlView.setImageResource(R.drawable.ic_play_white);
                } else {
                    getService().resume();
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
                .load(getService().getMetadata().getArtFile())
                .placeholder(R.drawable.fallback_cover)
                .error(R.drawable.fallback_cover)
                .into(artView);

        String trackInfo = getResources().getString(
                R.string.nowplaying_info_pattern,
                getService().getMetadata().getArtistTitle(),
                getService().getMetadata().getAlbumTitle());
        titleView.setText(getService().getMetadata().getTrackTitle());
        infoView.setText(trackInfo);


        if (getService().isPlaying()) {
            controlView.setImageResource(R.drawable.ic_pause_white);
        } else {
            controlView.setImageResource(R.drawable.ic_play_white);
        }
    }
}
