package com.edavtyan.materialplayer.components.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

import java.io.File;

public class NowPlayingArt implements NowPlayingMvp.View.Art {
	private final ImageView artView;
	private final TestableBitmapFactory bitmapFactory;

	public NowPlayingArt(NowPlayingActivity activity, TestableBitmapFactory bitmapFactory) {
		artView = activity.findView(R.id.art);
		this.bitmapFactory = bitmapFactory;
	}

	@Override
	public void setArt(File artFile) {
		Bitmap bitmap = bitmapFactory.fromPath(artFile.getAbsolutePath());

		if (bitmap != null) {
			artView.setImageBitmap(bitmap);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}
}
