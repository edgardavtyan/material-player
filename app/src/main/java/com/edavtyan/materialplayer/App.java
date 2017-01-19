package com.edavtyan.materialplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.album_all.AlbumListFactory;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailFactory;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailMvp;
import com.edavtyan.materialplayer.components.artist_all.ArtistListFactory;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailFactory;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp;
import com.edavtyan.materialplayer.components.audioeffects.AudioEffectsFactory;
import com.edavtyan.materialplayer.components.audioeffects.AudioEffectsMvp;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingFactory;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingFactory;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueFactory;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueMvp;
import com.edavtyan.materialplayer.components.player.PlayerFactory;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.track_all.TrackListFactory;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

import lombok.Setter;

public class App extends Application {

	private @Setter NowPlayingFactory nowPlayingFactory;
	private @Setter AlbumDetailFactory albumDetailFactory;

	public BaseFactory getBaseFactory(Activity activity) {
		return new BaseFactory(activity);
	}

	public AlbumListFactory getAlbumListDI(Context context, AlbumListMvp.View view) {
		return new AlbumListFactory(context, view);
	}

	public AlbumDetailFactory getAlbumDetailDI(Context context, AlbumDetailMvp.View view, int albumId) {
		if (albumDetailFactory == null)
			albumDetailFactory = new AlbumDetailFactory(context, view, albumId);
		return albumDetailFactory;
	}

	public ArtistListFactory getArtistListDI(Context context, ArtistListMvp.View view) {
		return new ArtistListFactory(context, view);
	}

	public ArtistDetailFactory getArtistDetailDI(
			Context context,
			ArtistDetailMvp.View view,
			String artistTitle) {
		return new ArtistDetailFactory(context, view, artistTitle);
	}

	public TrackListFactory getTrackListDI(Context context, TrackListMvp.View view) {
		return new TrackListFactory(context, view);
	}

	public NowPlayingFactory getNowPlayingFactory(
			NowPlayingActivity activity,
			NowPlayingMvp.View view) {
		if (nowPlayingFactory == null)
			nowPlayingFactory = new NowPlayingFactory(activity, view);
		return nowPlayingFactory;
	}

	public NowPlayingFloatingFactory getNowPlayingFloatingFactory(
			Context context,
			NowPlayingFloatingMvp.View view) {
		return new NowPlayingFloatingFactory(context, view);
	}

	public NowPlayingQueueFactory getPlaylistFactory(Context context, NowPlayingQueueMvp.View view) {
		return new NowPlayingQueueFactory(context, view);
	}

	public AudioEffectsFactory getAudioEffectsFactory(Context context, AudioEffectsMvp.View view) {
		return new AudioEffectsFactory(context, view);
	}

	public PlayerFactory getPlayerFactory(Context context) {
		return new PlayerFactory(context);
	}

	public PlayerNotificationFactory getPlayerNotificationFactory(
			Context context,
			int normalLayoutId,
			int bigLayoutId) {
		return new PlayerNotificationFactory(context, normalLayoutId, bigLayoutId);
	}

	public SdkFactory getSdkFactory() {
		return new SdkFactory();
	}
}
