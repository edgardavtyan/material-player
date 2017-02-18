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
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.main.MainFactory;
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

	private @Setter BaseFactory baseFactory;
	private @Setter NowPlayingFactory nowPlayingFactory;
	private @Setter AlbumDetailFactory albumDetailFactory;
	private @Setter AlbumListFactory albumListFactory;
	private @Setter ArtistDetailFactory artistDetailFactory;
	private @Setter ArtistListFactory artistListFactory;
	private @Setter AudioEffectsFactory audioEffectsFactory;
	private @Setter NowPlayingQueueFactory nowPlayingQueueFactory;
	private @Setter NowPlayingFloatingFactory nowPlayingFloatingFactory;
	private @Setter TrackListFactory trackListFactory;
	private @Setter SdkFactory sdkFactory;
	private @Setter MainFactory mainFactory;

	public BaseFactory getBaseFactory(Activity activity) {
		return (baseFactory == null)
				? new BaseFactory(activity)
				: baseFactory;
	}

	public AlbumListFactory getAlbumListDI(Context context, AlbumListMvp.View view) {
		return (albumListFactory == null)
				? new AlbumListFactory(context, view)
				: albumListFactory;
	}

	public AlbumDetailFactory getAlbumDetailDI(Context context, AlbumDetailMvp.View view, int albumId) {
		return (albumDetailFactory == null)
				? new AlbumDetailFactory(context, view, albumId)
				: albumDetailFactory;
	}

	public ArtistListFactory getArtistListDI(Context context, ArtistListMvp.View view) {
		return (artistListFactory == null)
				? new ArtistListFactory(context, view)
				: artistListFactory;
	}

	public ArtistDetailFactory getArtistDetailDI(
			Context context,
			ArtistDetailMvp.View view,
			String artistTitle) {
		return (artistDetailFactory == null)
				? new ArtistDetailFactory(context, view, artistTitle)
				: artistDetailFactory;
	}

	public TrackListFactory getTrackListDI(Context context, TrackListMvp.View view) {
		return (trackListFactory == null)
				? new TrackListFactory(context, view)
				: trackListFactory;
	}

	public NowPlayingFactory getNowPlayingFactory(
			NowPlayingActivity activity,
			NowPlayingMvp.View view) {
		return (nowPlayingFactory == null)
				? new NowPlayingFactory(activity, view)
				: nowPlayingFactory;
	}

	public NowPlayingFloatingFactory getNowPlayingFloatingFactory(
			Context context,
			NowPlayingFloatingMvp.View view) {
		return (nowPlayingFloatingFactory == null)
				? new NowPlayingFloatingFactory(context, view)
				: nowPlayingFloatingFactory;
	}

	public NowPlayingQueueFactory getPlaylistFactory(Context context, NowPlayingQueueMvp.View view) {
		return (nowPlayingQueueFactory == null)
				? new NowPlayingQueueFactory(context, view)
				: nowPlayingQueueFactory;
	}

	public AudioEffectsFactory getAudioEffectsFactory(Context context, AudioEffectsMvp.View view) {
		return (audioEffectsFactory == null)
				? new AudioEffectsFactory(context, view)
				: audioEffectsFactory;
	}

	public PlayerFactory getPlayerFactory(Context context) {
		return new PlayerFactory(context);
	}

	public PlayerNotificationFactory getPlayerNotificationFactory(Context context) {
		return new PlayerNotificationFactory(context, R.layout.notification, R.layout.notification_big);
	}

	public SdkFactory getSdkFactory() {
		return (sdkFactory == null)
				? new SdkFactory()
				: sdkFactory;
	}

	public MainFactory getMainFactory(MainActivity activity) {
		return (mainFactory == null) ? new MainFactory(activity) : mainFactory;
	}
}
