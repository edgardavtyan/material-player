package com.edavtyan.materialplayer.components.nowplaying.views;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.R;

import java.io.File;

public class NowPlayingArt {
	private final Context context;
	private final ImageView art;

	public NowPlayingArt(Activity activity) {
		this.context = activity;
		art = (ImageView) activity.findViewById(R.id.art);
	}

	public void setArt(File art) {
		Glide.with(context).load(art).error(R.drawable.fallback_cover).into(this.art);
	}
}
