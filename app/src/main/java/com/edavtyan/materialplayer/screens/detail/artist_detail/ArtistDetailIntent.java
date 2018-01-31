package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.Intent;

import lombok.Getter;

public class ArtistDetailIntent {
	private final @Getter String artistTitle;
	private final @Getter float sharedArtX;
	private final @Getter float sharedArtY;
	private final @Getter int sharedArtWidth;
	private final @Getter int sharedArtHeight;

	public ArtistDetailIntent(Intent intent) {
		artistTitle = intent.getStringExtra(ArtistDetailView.EXTRA_ARTIST_TITLE);
		sharedArtX = intent.getFloatExtra(ArtistDetailView.SHARED_ART_X, 0f);
		sharedArtY= intent.getFloatExtra(ArtistDetailView.SHARED_ART_Y, 0f);
		sharedArtWidth = intent.getIntExtra(ArtistDetailView.SHARED_ART_WIDTH, 0);
		sharedArtHeight = intent.getIntExtra(ArtistDetailView.SHARED_ART_HEGIHT, 0);
	}
}
