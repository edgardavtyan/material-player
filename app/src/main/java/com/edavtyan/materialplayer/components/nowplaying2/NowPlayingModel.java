package com.edavtyan.materialplayer.components.nowplaying2;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;

public class NowPlayingModel {
	private MusicPlayerService service;
	private MusicPlayer player;
	private NowPlayingQueue queue;

	public NowPlayingModel(MusicPlayerService service) {
		this.player = service.getPlayer();
		this.queue = service.getQueue();
	}

	public CharSequence getTrackTitle() {
		return queue.getCurrentTrack().getTitle();
	}

	public CharSequence getArtistTitle() {
		return queue.getCurrentTrack().getArtistTitle();
	}

	public CharSequence getAlbumTitle() {
		return queue.getCurrentTrack().getAlbumTitle();
	}
}
