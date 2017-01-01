package com.edavtyan.materialplayer.components.now_playing;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingPresenterTest extends BaseTest {
	private NowPlayingMvp.Model model;
	private NowPlayingMvp.View view;
	private NowPlayingMvp.Presenter presenter;
	private NowPlayingMvp.View.Controls controls;
	private NowPlayingMvp.View.Info info;
	private NowPlayingMvp.View.Art art;
	private NowPlayingMvp.View.Seekbar seekbar;

	@Override public void beforeEach() {
		super.beforeEach();

		model = mock(NowPlayingMvp.Model.class);

		controls = mock(NowPlayingMvp.View.Controls.class);
		info = mock(NowPlayingMvp.View.Info.class);
		art = mock(NowPlayingMvp.View.Art.class);
		seekbar = mock(NowPlayingMvp.View.Seekbar.class);

		view = mock(NowPlayingMvp.View.class);
		when(view.getControls()).thenReturn(controls);
		when(view.getInfo()).thenReturn(info);
		when(view.getArt()).thenReturn(art);
		when(view.getSeekbar()).thenReturn(seekbar);

		runOnUiThread(() -> presenter = new NowPlayingPresenter(model, view));
	}

	@Test public void constructor_setOnModelBoundListener() {
		verify(model).setOnModelBoundListener(presenter);
	}

	@Test public void bind_bindModel() {
		presenter.bind();
		verify(model).bind();
	}

	@Test public void unbind_unbindModel() {
		presenter.unbind();
		verify(model).unbind();
	}

	@Test public void onModelConnected_initView() {
		Bitmap artBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);

		when(model.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		when(model.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		when(model.isPlaying()).thenReturn(true);
		when(model.getTitle()).thenReturn("title");
		when(model.getArtist()).thenReturn("artist");
		when(model.getAlbum()).thenReturn("album");
		when(model.getDuration()).thenReturn(8000);
		when(model.getPosition()).thenReturn(5000);
		when(model.getArt()).thenReturn(artBitmap);

		presenter.onModelBound();

		verify(controls).setRepeatMode(RepeatMode.REPEAT_ALL);
		verify(controls).setShuffleMode(ShuffleMode.ENABLED);
		verify(controls).setIsPlaying(true);
		verify(info).setTitle("title");
		verify(info).setInfo("artist", "album");
		verify(seekbar).setDuration(8000);
		verify(seekbar).setPositionText(5000);
		verify(seekbar).setPosition(5000);
		verify(seekbar).setPositionText(5000);
		verify(art).setArt(artBitmap);
	}

	@Test public void onNewTrack_updateView() {
		testViewUpdate(presenter::onNewTrack);
	}

	@Test public void onPlayPause_updatePlayPauseIcon() {
		when(model.isPlaying()).thenReturn(true);
		presenter.onPlayPause();
		verify(controls).setIsPlaying(true);
	}

	@Test public void onFabClick_gotoPlaylistScreen() {
		presenter.onFabClick();
		verify(view).gotoPlaylistScreen();
	}

	@Test public void onTrackSeekChanged_setTrackPositionText() {
		presenter.onSeekChanged(9000);
		verify(seekbar).setPositionText(9000);
	}

	@Test public void onTrackSeekStop_seekTrack() {
		presenter.onSeekStop(9000);
		verify(model).seek(9000);
	}

	@Test public void onShuffleClick_toggleShuffleMode() {
		presenter.onShuffleClick();
		verify(model).toggleShuffleMode();
	}

	@Test public void onShuffleClick_setShuffleViewMode() {
		when(model.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		presenter.onShuffleClick();
		verify(controls).setShuffleMode(model.getShuffleMode());
	}

	@Test public void onRewindClick_rewindTrackViaModel() {
		presenter.onRewindClick();
		verify(model).rewind();
	}

	@Test public void onPlayPauseClick_togglePlayPauseMode() {
		when(model.isPlaying()).thenReturn(true);
		presenter.onPlayPauseClick();
		verify(model).playPause();
	}

	@Test public void onPlayPauseClick_setPlayPauseViewMode() {
		when(model.isPlaying()).thenReturn(true);
		presenter.onPlayPauseClick();
		verify(controls).setIsPlaying(model.isPlaying());
	}

	@Test public void onFastForwardClick_fastForwardTrackViaModel() {
		presenter.onFastForwardClick();
		verify(model).fastForward();
	}

	@Test public void onRepeatClick_toggleRepeatMode() {
		presenter.onRepeatClick();
		verify(model).toggleRepeatMode();
	}

	@Test public void onRepeatClick_setRepeatViewMode() {
		when(model.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		presenter.onRepeatClick();
		verify(controls).setRepeatMode(model.getRepeatMode());
	}

	private void testViewUpdate(Runnable presenterCall) {
		Bitmap artBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);

		when(model.getTitle()).thenReturn("title");
		when(model.getArtist()).thenReturn("artist");
		when(model.getAlbum()).thenReturn("album");
		when(model.getPosition()).thenReturn(3000);
		when(model.getDuration()).thenReturn(9000);
		when(model.getArt()).thenReturn(artBitmap);

		presenterCall.run();

		verify(info).setTitle("title");
		verify(info).setInfo("artist", "album");
		verify(seekbar).setPosition(3000);
		verify(seekbar).setPositionText(3000);
		verify(seekbar).setDuration(9000);
		verify(seekbar).setDurationText(9000);
		verify(art).setArt(artBitmap);
	}
}
