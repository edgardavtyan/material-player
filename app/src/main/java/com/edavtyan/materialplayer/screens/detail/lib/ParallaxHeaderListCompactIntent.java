package com.edavtyan.materialplayer.screens.detail.lib;

import android.content.Intent;

import lombok.Getter;

public class ParallaxHeaderListCompactIntent {
	private final @Getter float sharedArtX;
	private final @Getter float sharedArtY;
	private final @Getter int sharedArtWidth;
	private final @Getter int sharedArtHeight;

	public ParallaxHeaderListCompactIntent(Intent intent) {
		sharedArtX = intent.getFloatExtra(ParallaxHeaderListCompactModule.SHARED_ART_X, 0f);
		sharedArtY= intent.getFloatExtra(ParallaxHeaderListCompactModule.SHARED_ART_Y, 0f);
		sharedArtWidth = intent.getIntExtra(ParallaxHeaderListCompactModule.SHARED_ART_WIDTH, 0);
		sharedArtHeight = intent.getIntExtra(ParallaxHeaderListCompactModule.SHARED_ART_HEIGHT, 0);
	}
}
