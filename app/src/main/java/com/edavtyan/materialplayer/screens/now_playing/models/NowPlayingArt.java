package com.edavtyan.materialplayer.screens.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingArt {
	@BindView(R.id.art) @Getter ImageView artView;
	@BindView(R.id.shared_art) @Getter ImageView sharedArtView;

	public NowPlayingArt(NowPlayingActivity activity) {
		ButterKnife.bind(this, activity);
	}

	public void setArt(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
			sharedArtView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
			sharedArtView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
