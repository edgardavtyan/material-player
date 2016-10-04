package com.edavtyan.materialplayer.components.now_playing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.utils.ArtProvider;

import java.io.File;

import lombok.Setter;

public class NowPlayingModel implements NowPlayingMvp.Model {
	private final Context context;

	private MusicPlayerService service;

	@Setter OnModelBoundListener onModelBoundListener;

	public NowPlayingModel(Context context) {
		this.context = context;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, MusicPlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public NowPlayingMvp.RepeatState getRepeatMode() {
		switch (service.getQueue().getRepeatMode()) {
		case NO_REPEAT:
			return NowPlayingMvp.RepeatState.DISABLED;
		case REPEAT:
			return NowPlayingMvp.RepeatState.REPEAT_ALL;
		case REPEAT_ONE:
			return NowPlayingMvp.RepeatState.REPEAT_ONE;
		default:
			return NowPlayingMvp.RepeatState.DISABLED;
		}
	}

	@Override
	public NowPlayingMvp.PlayPauseState getPlayPauseMode() {
		return service.getPlayer().isPlaying()
				? NowPlayingMvp.PlayPauseState.PLAYING
				: NowPlayingMvp.PlayPauseState.PAUSED;
	}

	@Override
	public NowPlayingMvp.ShuffleState getShuffleMode() {
		return service.getQueue().isShuffling()
				? NowPlayingMvp.ShuffleState.ENABLED
				: NowPlayingMvp.ShuffleState.DISABLED;
	}

	@Override
	public void toggleRepeatMode() {
		service.getQueue().toggleRepeatMode();
	}

	@Override
	public void toggleShuffleMode() {
		service.getQueue().toggleShuffling();
	}

	@Override
	public void togglePlayPauseMode() {
		if (service.getPlayer().isPlaying()) {
			service.getPlayer().pause();
		} else {
			service.getPlayer().resume();
		}
	}

	@Override
	public String getTitle() {
		return service.getQueue().getCurrentTrack().getTitle();
	}

	@Override
	public String getArtist() {
		return service.getQueue().getCurrentTrack().getArtistTitle();
	}

	@Override
	public String getAlbum() {
		return service.getQueue().getCurrentTrack().getAlbumTitle();
	}

	@Override
	public int getDuration() {
		return (int) service.getQueue().getCurrentTrack().getDuration();
	}

	@Override
	public int getPosition() {
		return service.getPlayer().getPosition();
	}

	@Override
	public File getArt() {
		return ArtProvider.fromTrack(service.getQueue().getCurrentTrack());
	}

	@Override
	public void seek(int positionMillis) {
		service.getPlayer().setPosition(positionMillis);
	}

	@Override
	public void rewind() {
		service.getPlayer().movePrev();
		service.getPlayer().prepare();
	}

	@Override
	public void fastForward() {
		service.getPlayer().moveNext();
		service.getPlayer().prepare();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
		if (onModelBoundListener != null) {
			onModelBoundListener.onModelBound();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
