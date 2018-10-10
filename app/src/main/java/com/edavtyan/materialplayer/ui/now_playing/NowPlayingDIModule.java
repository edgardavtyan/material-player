package com.edavtyan.materialplayer.ui.now_playing;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingLyrics;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.ui.now_playing_queue.NowPlayingQueueFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingDIModule {
	private final NowPlayingActivity activity;

	public NowPlayingDIModule(NowPlayingActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public NowPlayingActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public NowPlayingQueueFragment provideNowPlayingQueueFragment() {
		return (NowPlayingQueueFragment) activity.getSupportFragmentManager()
												 .findFragmentById(R.id.fragment_queue);
	}

	@Provides
	@ActivityScope
	public NowPlayingModel provideModel(
			NowPlayingActivity activity,
			AlbumArtProvider albumArtProvider,
			LyricsVisiblePref lyricsVisiblePref) {
		return new NowPlayingModel(activity, albumArtProvider, lyricsVisiblePref);
	}

	@Provides
	@ActivityScope
	public NowPlayingPresenter providePresenter(NowPlayingModel model, NowPlayingActivity view) {
		return new NowPlayingPresenter(model, view);
	}

	@Provides
	@ActivityScope
	public NowPlayingArt provideArtPartial(NowPlayingActivity activity) {
		return new NowPlayingArt(activity);
	}

	@Provides
	@ActivityScope
	public NowPlayingControls provideControlsPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingControls(activity, presenter);
	}

	@Provides
	@ActivityScope
	public NowPlayingFab provideFabPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingFab(activity, presenter);
	}

	@Provides
	@ActivityScope
	public NowPlayingInfo provideInfoPartial(NowPlayingActivity activity) {
		return new NowPlayingInfo(activity);
	}

	@Provides
	@ActivityScope
	public NowPlayingSeekbar provideSeekbarPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingSeekbar(activity, presenter);
	}

	@Provides
	@ActivityScope
	public NowPlayingLyrics provideLyricsPartial(NowPlayingActivity activity) {
		return new NowPlayingLyrics(activity);
	}

	@Provides
	@ActivityScope
	public LyricsVisiblePref provideLyricsVisiblePref(AdvancedSharedPrefs prefs) {
		return new LyricsVisiblePref(prefs);
	}

	@Provides
	@ActivityScope
	public QueueRevealAnimation provideQueueRevealAnimation(NowPlayingQueueFragment queueFragment) {
		return new QueueRevealAnimation(activity, queueFragment);
	}
}
