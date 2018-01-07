package com.edavtyan.materialplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsActivity;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsFactory;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsViewComponent;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsViewFactory;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsViewModule;
import com.edavtyan.materialplayer.components.audio_effects.DaggerAudioEffectsViewComponent;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailComponent;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailFactory;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailModule;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailView;
import com.edavtyan.materialplayer.components.detail.album_detail.DaggerAlbumDetailComponent;
import com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailFactory;
import com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailView;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListFactory;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListView;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListFactory;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListView;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListFactory;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListFragment;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.main.MainFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingFactory;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingFactory;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingFragment;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueFactory;
import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerFactory;
import com.edavtyan.materialplayer.components.player.receivers.ReceiversFactory;
import com.edavtyan.materialplayer.components.search.album.SearchAlbumFactory;
import com.edavtyan.materialplayer.components.search.album.SearchAlbumFragment;
import com.edavtyan.materialplayer.components.search.artist.SearchArtistFactory;
import com.edavtyan.materialplayer.components.search.artist.SearchArtistFragment;
import com.edavtyan.materialplayer.components.search.tracks.SearchTrackFactory;
import com.edavtyan.materialplayer.components.search.tracks.SearchTrackFragment;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import lombok.Setter;

public class App extends Application {

	private @Setter BaseFactory baseFactory;
	private @Setter NowPlayingFactory nowPlayingFactory;
	private @Setter AlbumDetailFactory albumDetailFactory;
	private @Setter AlbumListFactory albumListFactory;
	private @Setter ArtistDetailFactory artistDetailFactory;
	private @Setter ArtistListFactory artistListFactory;
	private @Setter AudioEffectsFactory audioEffectsFactory;
	private @Setter AudioEffectsViewFactory audioEffectsViewFactory;
	private @Setter NowPlayingQueueFactory nowPlayingQueueFactory;
	private @Setter NowPlayingFloatingFactory nowPlayingFloatingFactory;
	private @Setter TrackListFactory trackListFactory;
	private @Setter SdkFactory sdkFactory;
	private @Setter MainFactory mainFactory;
	private @Setter SearchArtistFactory searchArtistFactory;
	private @Setter SearchAlbumFactory searchAlbumFactory;
	private @Setter SearchTrackFactory searchTrackFactory;
	private @Setter ReceiversFactory receiversFactory;

	private @Setter AudioEffectsViewComponent audioEffectsComponent;
	private @Setter AlbumDetailComponent albumDetailComponent;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public AudioEffectsViewComponent getAudioEffectsComponent(AudioEffectsActivity activity) {
		if (audioEffectsComponent == null) {
			audioEffectsComponent = DaggerAudioEffectsViewComponent
					.builder()
					.modelModulesModule(new ModelModulesModule(activity))
					.audioEffectsViewModule(new AudioEffectsViewModule(activity))
					.build();
		}

		return audioEffectsComponent;
	}

	public AlbumDetailComponent getAlbumDetailComponent(
			Context context,
			AlbumDetailView view,
			int albumId) {
		return DaggerAlbumDetailComponent
				.builder()
				.modelModulesModule(new ModelModulesModule(context))
				.albumArtModule(new AlbumArtModule())
				.daggerDBModule(new DaggerDBModule())
				.albumDetailModule(new AlbumDetailModule(context, view, albumId))
				.utilsModule(new UtilsModule())
				.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
				.build();
	}

	public BaseFactory getBaseFactory(Activity activity) {
		return (baseFactory == null)
				? new BaseFactory(activity)
				: baseFactory;
	}

	public AlbumListFactory getAlbumListDI(Context context, AlbumListView view) {
		return (albumListFactory == null)
				? new AlbumListFactory(context, view)
				: albumListFactory;
	}

	public AlbumDetailFactory getAlbumDetailDI(Context context, AlbumDetailView view, int albumId) {
		return (albumDetailFactory == null)
				? new AlbumDetailFactory(context, view, albumId)
				: albumDetailFactory;
	}

	public ArtistListFactory getArtistListDI(Context context, ArtistListView view) {
		return (artistListFactory == null)
				? new ArtistListFactory(context, view)
				: artistListFactory;
	}

	public ArtistDetailFactory getArtistDetailDI(
			Context context,
			ArtistDetailView view,
			String artistTitle) {
		return (artistDetailFactory == null)
				? new ArtistDetailFactory(context, view, artistTitle)
				: artistDetailFactory;
	}

	public TrackListFactory getTrackListDI(Context context, TrackListFragment view) {
		return (trackListFactory == null)
				? new TrackListFactory(context, view)
				: trackListFactory;
	}

	public NowPlayingFactory getNowPlayingFactory(NowPlayingActivity activity) {
		return (nowPlayingFactory == null)
				? new NowPlayingFactory(activity)
				: nowPlayingFactory;
	}

	public NowPlayingFloatingFactory getNowPlayingFloatingFactory(
			Context context,
			NowPlayingFloatingFragment view) {
		return (nowPlayingFloatingFactory == null)
				? new NowPlayingFloatingFactory(context, view)
				: nowPlayingFloatingFactory;
	}

	public NowPlayingQueueFactory getPlaylistFactory(Context context, NowPlayingQueueActivity view) {
		return (nowPlayingQueueFactory == null)
				? new NowPlayingQueueFactory(context, view)
				: nowPlayingQueueFactory;
	}

	public AudioEffectsViewFactory getAudioEffectsFactory(Context context, AudioEffectsActivity view) {
		return (audioEffectsViewFactory == null)
				? new AudioEffectsViewFactory(context, view)
				: audioEffectsViewFactory;
	}

	public AudioEffectsFactory getAudioEffectsFactory(Context context, Player player) {
		return (audioEffectsFactory == null)
				? new AudioEffectsFactory(context, player)
				: audioEffectsFactory;
	}

	public PlayerFactory getPlayerFactory(Context context) {
		return new PlayerFactory(context);
	}

	public PlayerNotificationFactory getPlayerNotificationFactory(Context context) {
		return new PlayerNotificationFactory(context);
	}

	public SdkFactory getSdkFactory() {
		return (sdkFactory == null)
				? new SdkFactory()
				: sdkFactory;
	}

	public SearchArtistFactory getSearchArtistFactory(Context context, SearchArtistFragment view) {
		return (searchArtistFactory == null)
				? new SearchArtistFactory(context, view)
				: searchArtistFactory;
	}

	public SearchAlbumFactory getSearchAlbumFactory(Context context, SearchAlbumFragment view) {
		return (searchAlbumFactory == null)
				? new SearchAlbumFactory(context, view)
				: searchAlbumFactory;
	}

	public SearchTrackFactory getSearchTrackFactory(Context context, SearchTrackFragment view) {
		return (searchTrackFactory == null)
				? new SearchTrackFactory(context, view)
				: searchTrackFactory;
	}

	public ReceiversFactory getReceiversFactory(Context context, Player player) {
		return (receiversFactory == null)
				? new ReceiversFactory(context, player)
				: receiversFactory;
	}

	public MainFactory getMainFactory(MainActivity activity) {
		return (mainFactory == null) ? new MainFactory(activity) : mainFactory;
	}
}
