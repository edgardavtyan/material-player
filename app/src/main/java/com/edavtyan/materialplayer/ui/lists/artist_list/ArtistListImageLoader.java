package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;

public class ArtistListImageLoader {
	private final LastfmApi lastfmApi;
	private final ArtistListImageLinkCache linkCache;

	public ArtistListImageLoader(LastfmApi lastfmApi, ArtistListImageLinkCache linkCache) {
		this.lastfmApi = lastfmApi;
		this.linkCache = linkCache;
	}

	@Nullable
	public String getImageLink(String artistTitle) {
		if (linkCache.exists(artistTitle)) {
			return linkCache.getLink(artistTitle);
		}

		String imageLink = lastfmApi.getArtistInfo(artistTitle).getLargeImageUrl();
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
