package com.edavtyan.materialplayer.ui.now_playing.models;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingArt {
	@BindView(R.id.art) @Getter ImageView artView;
	@BindView(R.id.shared_art) @Getter ImageView sharedArtView;
	@BindView(R.id.art_shadow) @Getter View shadowView;

	public NowPlayingArt(NowPlayingActivity activity) {
		ButterKnife.bind(this, activity);
	}

	public void setArt(@Nullable Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
			sharedArtView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
			sharedArtView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
