package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.music_api.MusicApi;

public class ArtistListImageLoader {
	private final MusicApi musicApi;
	private final ArtistListImageLinkCache linkCache;

	public ArtistListImageLoader(MusicApi musicApi, ArtistListImageLinkCache linkCache) {
		this.musicApi = musicApi;
		this.linkCache = linkCache;
	}

	@Nullable
	public String getImageLink(String artistTitle) {
		if (linkCache.exists(artistTitle)) {
			return linkCache.getLink(artistTitle);
		}

		String imageLink = musicApi.getArtistInfo(artistTitle).getImageUrl();
		linkCache.addLink(artistTitle, imageLink);
		return imageLink;
	}

	public boolean isCached(String artistTitle) {
		return linkCache.exists(artistTitle);
	}

	public String getLinkFromCache(String artistTitle) {
		return linkCache.getLink(artistTitle);
	}
}
