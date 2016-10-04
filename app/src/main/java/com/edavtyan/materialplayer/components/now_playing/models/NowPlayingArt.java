package com.edavtyan.materialplayer.components.now_playing.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

import java.io.File;

public class NowPlayingArt implements NowPlayingMvp.View.Art {
	private final ImageView artView;

	public NowPlayingArt(NowPlayingActivity activity) {
		artView = activity.findView(R.id.art);
	}

	@Override
	public void setArt(File artFile) {
		Bitmap bitmap = BitmapFactory.decodeFile(artFile.getAbsolutePath());

		if (bitmap != null) {
			artView.setImageBitmap(bitmap);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
