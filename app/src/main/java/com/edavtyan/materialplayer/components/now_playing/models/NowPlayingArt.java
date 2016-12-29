package com.edavtyan.materialplayer.components.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

public class NowPlayingArt implements NowPlayingMvp.View.Art {
	private final ImageView artView;

	public NowPlayingArt(NowPlayingActivity activity) {
		artView = activity.findView(R.id.art);
	}

	@Override
	public void setArt(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
