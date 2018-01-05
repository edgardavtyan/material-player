package com.edavtyan.materialplayer.components.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingArt {
	@BindView(R.id.art) ImageView artView;

	public NowPlayingArt(NowPlayingActivity activity) {
		ButterKnife.bind(this, activity);
	}

	public void setArt(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
