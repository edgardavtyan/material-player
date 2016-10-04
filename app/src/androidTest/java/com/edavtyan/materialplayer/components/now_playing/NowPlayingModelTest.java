package com.edavtyan.materialplayer.components.now_playing;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp.Model.OnModelBoundListener;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.asertions.IntentAssert;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO: unify repeat, shuffle and play-pause modes
public class NowPlayingModelTest extends BaseTest {
	private NowPlayingMvp.Model model;
	private MusicPlayerBinder binder;
	private NowPlayingQueue queue;
	private MusicPlayer player;

	@Override
	public void beforeEach() {
		super.beforeEach();

		queue = mock(NowPlayingQueue.class);
		player = mock(MusicPlayer.class);

		MusicPlayerService service = mock(MusicPlayerService.class);
		when(service.getQueue()).thenReturn(queue);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
			model = new NowPlayingModel(context);
			model.onServiceConnected(null, binder);
		});
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindServiceWithCorrectIntent() {
		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);

		NowPlayingMvp.Model model = new NowPlayingModel(context);
		model.bind();

		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		IntentAssert.assertThat(intentCaptor.getValue()).classEqualTo(MusicPlayerService.class);
	}

	@Test
	public void unbind_unbindService() {
		model.bind();
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void getRepeatMode_noRepeat_returnDisabled() {
		testRepeatMode(RepeatMode.NO_REPEAT, NowPlayingMvp.RepeatState.DISABLED);
	}

	@Test
	public void getRepeatMode_repeatOne_returnRepeatOne() {
		testRepeatMode(RepeatMode.REPEAT_ONE, NowPlayingMvp.RepeatState.REPEAT_ONE);
	}

	@Test
	public void getRepeatMode_repeat_returnRepeatAll() {
		testRepeatMode(RepeatMode.REPEAT, NowPlayingMvp.RepeatState.REPEAT_ALL);
	}

	@Test
	public void toggleRepeatMode_callService() {
		model.toggleRepeatMode();
		verify(queue).toggleRepeatMode();
	}

	@Test
	public void getShuffleMode_true_returnEnabled() {
		testShuffleMode(true, NowPlayingMvp.ShuffleState.ENABLED);
	}

	@Test
	public void getShuffleMode_false_returnDisabled() {
		testShuffleMode(false, NowPlayingMvp.ShuffleState.DISABLED);
	}

	@Test
	public void toggleShuffle() {
		model.toggleShuffleMode();
		verify(queue).toggleShuffling();
	}

	@Test
	public void getPlayPauseMode_true_returnPlaying() {
		testPlayPauseMode(true, NowPlayingMvp.PlayPauseState.PLAYING);
	}

	@Test
	public void getPlayPauseMode_false_returnPaused() {
		testPlayPauseMode(false, NowPlayingMvp.PlayPauseState.PAUSED);
	}

	@Test
	public void togglePlayPause_playing_pausePlayer() {
		when(player.isPlaying()).thenReturn(true);
		model.togglePlayPauseMode();
		verify(player).pause();
	}

	@Test
	public void togglePlayPause_paused_resumePlayer() {
		when(player.isPlaying()).thenReturn(false);
		model.togglePlayPauseMode();
		verify(player).resume();
	}

	@Test
	public void getTitle_returnTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setTitle("title");
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(model.getTitle()).isEqualTo("title");
	}

	@Test
	public void getArtist_returnArtistTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setArtistTitle("artist");
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(model.getArtist()).isEqualTo("artist");
	}

	@Test
	public void getAlbum_returnAlbumTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setAlbumTitle("album");
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(model.getAlbum()).isEqualTo("album");
	}

	@Test
	public void getDuration_returnDurationOfNowPlayingTrack() {
		Track track = new Track();
		track.setDuration(1234);
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(model.getDuration()).isEqualTo(1234);
	}

	@Test
	public void getPosition_returnPositionOfNowPlayingTrack() {
		when(player.getPosition()).thenReturn(5678);
		assertThat(model.getPosition()).isEqualTo(5678);
	}

	@Test
	@Ignore("Unit test not implemented")
	public void getTrackArt() throws Exception {
	}

	@Test
	public void seek_setPlayerPosition() {
		model.seek(1357);
		verify(player).setPosition(1357);
	}

	@Test
	public void rewind_rewindPlayerViaService() {
		model.rewind();
		verify(player).movePrev();
		verify(player).prepare();
	}

	@Test
	public void fastForward_fastForwardPlayerViaService() {
		model.fastForward();
		verify(player).moveNext();
		verify(player).prepare();
	}

	@Test
	public void onServiceConnected_callOnModelConnectedListener() {
		OnModelBoundListener listener = mock(OnModelBoundListener.class);
		model.setOnModelBoundListener(listener);
		model.onServiceConnected(null, binder);
		verify(listener).onModelBound();
	}

	private void testPlayPauseMode(boolean actual, NowPlayingMvp.PlayPauseState expected) {
		when(player.isPlaying()).thenReturn(actual);
		assertThat(model.getPlayPauseMode()).isEqualTo(expected);
	}

	private void testRepeatMode(RepeatMode actual, NowPlayingMvp.RepeatState expected) {
		when(queue.getRepeatMode()).thenReturn(actual);
		assertThat(model.getRepeatMode()).isEqualTo(expected);
	}

	private void testShuffleMode(boolean actual, NowPlayingMvp.ShuffleState expected) {
		when(queue.isShuffling()).thenReturn(actual);
		assertThat(model.getShuffleMode()).isEqualTo(expected);
	}
}
