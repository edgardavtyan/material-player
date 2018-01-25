package com.edavtyan.materialplayer.player;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.player.engines.AudioEngine;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static com.ed.libsutils.assertj.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class PlayerTest extends BaseTest {
	private SharedPreferences basePrefs;
	private PlayerPrefs prefs;
	private AudioEngine audioEngine;
	private PlayerQueue queue;
	private Player player;
	private PlayerQueueStorage queueStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs = new PlayerPrefs(new AdvancedSharedPrefs(basePrefs));
		audioEngine = mock(AudioEngine.class);
		queue = mock(PlayerQueue.class);
		queueStorage = mock(PlayerQueueStorage.class);
		player = new Player(audioEngine, queue, prefs, queueStorage);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void constructor_setDefaultShuffleMode() {
		verify(queue).setShuffleMode(ShuffleMode.DISABLED);
	}

	@Test
	public void constructor_setDefaultRepeatMode() {
		verify(queue).setRepeatMode(RepeatMode.DISABLED);
	}

	@Test
	public void constructor_setShuffleModeFromPrefs() {
		prefs.saveShuffleMode(ShuffleMode.ENABLED);
		player = new Player(audioEngine, queue, prefs, queueStorage);
		verify(queue).setShuffleMode(ShuffleMode.ENABLED);
	}

	@Test
	public void constructor_setRepeatModeFromPrefs() {
		prefs.saveRepeatMode(RepeatMode.REPEAT_ALL);
		player = new Player(audioEngine, queue, prefs, queueStorage);
		verify(queue).setRepeatMode(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void constructor_setSavedTracksFromStorage() {
		List<Track> tracks = new ArrayList<>();
		when(queueStorage.load()).thenReturn(tracks);

		reset(queue);
		player = new Player(audioEngine, queue, prefs, queueStorage);

		verify(queue).addManyTracks(tracks);
	}

	@Test
	public void constructor_queueHasData_prepareTrackAtPositionFromPrefs() {
		Track track = new Track();
		track.setPath("path_600");
		when(queue.hasData()).thenReturn(true);
		when(queue.getCurrentTrack()).thenReturn(track);
		prefs.saveCurrentIndex(600);

		player = new Player(audioEngine, queue, prefs, queueStorage);

		verify(queue).setCurrentIndex(600);
		verify(audioEngine).prepareTrack("path_600");
	}

	@Test
	public void addTrack_addTrackToQueue() {
		Track track = mock(Track.class);
		player.addTrack(track);
		verify(queue).addTrack(track);
	}

	@Test
	public void addManyTracks_addTracksToQueue() {
		List tracks = mock(List.class);
		player.addManyTracks(tracks);
		verify(queue).addManyTracks(tracks);
	}

	@Test
	public void removeTrackAt_removeTrackFromQueue() {
		player.removeTrackAt(9);
		verify(queue).removeTrackAt(9);
	}

	@Test
	public void getTrackAt_returnTrackFromQueue() {
		Track track = mock(Track.class);
		when(queue.getTrackAt(7)).thenReturn(track);
		assertThat(player.getTrackAt(7)).isSameAs(track);
	}

	@Test
	public void getTracksCount_returnCountFromQueue() {
		when(queue.getTracksCount()).thenReturn(15);
		assertThat(player.getTracksCount()).isEqualTo(15);
	}

	@Test
	public void playNewTracks_addTracksToQueueAndPlaySelected() {
		List tracks = mock(List.class);
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);

		player.playNewTracks(tracks, 6);

		verify(queue).replaceTracks(tracks, 6);
		verify(audioEngine).playTrack("path");
	}

	@Test
	public void playTrackAt_playTrackAtGivenPosition() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);

		player.playTrackAt(4);

		InOrder order = inOrder(queue, audioEngine);
		order.verify(queue).setCurrentIndex(4);
		order.verify(audioEngine).playTrack("path");
	}

	@Test
	public void playTrackAt_savePositionToPrefs() {
		when(queue.getCurrentTrack()).thenReturn(new Track());
		player.playTrackAt(1400);
		assertThat(prefs.getCurrentIndex()).isEqualTo(1400);
	}

	@Test
	public void play_playAudioEngine() {
		player.play();
		verify(audioEngine).play();
	}

	@Test
	public void pause_pauseAudioEngine() {
		player.pause();
		verify(audioEngine).pause();
	}

	@Test
	public void skipToNext_moveQueueToNextAndStartEngine() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);
		when(queue.hasData()).thenReturn(true);

		player.skipToNext();

		InOrder order = inOrder(queue, audioEngine);
		order.verify(queue).moveToNext();
		order.verify(audioEngine).playTrack("path");
	}

	@Test
	public void skipToNext_queueHasData_saveNewPositionToPrefs() {
		when(queue.hasData()).thenReturn(true);
		when(queue.getCurrentIndex()).thenReturn(1800);
		when(queue.getCurrentTrack()).thenReturn(new Track());
		player.skipToNext();
		assertThat(prefs.getCurrentIndex()).isEqualTo(1800);
	}

	@Test
	public void skipToNext_queueEnded_notPlayAnything() {
		when(queue.hasData()).thenReturn(true);
		when(queue.isEnded()).thenReturn(true);
		player.skipToNext();
		verify(audioEngine, never()).playTrack(any());
	}

	@Test
	public void skipToPrevious_playedLessThatFiveSeconds_playPreviousTrack() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);
		when(audioEngine.getSeek()).thenReturn(4999L);

		player.skipToPrevious();

		verify(queue).moveToPrev();
		verify(audioEngine).playTrack("path");
	}

	@Test
	public void skipToPrevious_playedFiveSecondsOrMore_playFromBeginning() {
		when(audioEngine.getSeek()).thenReturn(5000L);
		player.skipToPrevious();
		verify(audioEngine).setSeek(0);
	}

	@Test
	public void skipToPrevious_saveNewPositionToPrefs() {
		when(queue.getCurrentTrack()).thenReturn(new Track());
		when(queue.getCurrentIndex()).thenReturn(2200);
		player.skipToPrevious();
		assertThat(prefs.getCurrentIndex()).isEqualTo(2200);
	}

	@Test
	public void getShuffleMode_returnModeFromQueue() {
		when(queue.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		assertThat(player.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test
	public void toggleShuffleMode_callQueue() {
		player.toggleShuffleMode();
		verify(queue).toggleShuffleMode();
	}

	@Test
	public void toggleShuffleMode_saveShuffleMode() {
		when(queue.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		player.toggleShuffleMode();
		assertThat(prefs.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test
	public void getRepeatMode_returnModeFromQueue() {
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		assertThat(player.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void toggleRepeatMode_callQueue() {
		player.toggleRepeatMode();
		verify(queue).toggleRepeatMode();
	}

	@Test
	public void toggleRepeatMode_saveRepeatMode() {
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		player.toggleRepeatMode();
		assertThat(prefs.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void getCurrentTrack_returnTrackFromQueue() {
		Track track = mock(Track.class);
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(player.getCurrentTrack()).isSameAs(track);
	}

	@Test
	public void isPlaying_returnFromEngine() {
		when(audioEngine.isPlaying()).thenReturn(true);
		assertThat(player.isPlaying()).isEqualTo(true);
	}

	@Test
	public void playPause_callOnPlayPauseListener() {
		Player.OnPlayPauseListener listener = mock(Player.OnPlayPauseListener.class);
		player.setOnPlayPauseListener(listener);
		player.playPause();
		verify(listener).onPlayPause();
	}

	@Test
	public void hasData_returnFromQueue() {
		when(queue.hasData()).thenReturn(true);
		assertThat(player.hasData()).isTrue();
	}

	@Test
	public void getSeek_returnSeekFromEngine() {
		when(audioEngine.getSeek()).thenReturn(120L);
		assertThat(player.getSeek()).isEqualTo(120L);
	}

	@Test
	public void setSeek_callEngine() {
		player.setSeek(60);
		verify(audioEngine).setSeek(60);
	}

	@Test
	public void lowerVolume_setAudioEngineVolume() {
		player.lowerVolume();
		verify(audioEngine).setVolume(0.3f);
	}

	@Test
	public void restoreVolume_setAudioEngineVolume() {
		player.restoreVolume();
		verify(audioEngine).setVolume(1.0f);
	}

	@Test
	public void onPrepared_OnNewTrackListenerSet_callOnNewTrackListener() {
		Player.OnNewTrackListener listener = mock(Player.OnNewTrackListener.class);
		player.setOnNewTrackListener(listener);
		player.onPrepared();
		verify(listener).onNewTrack();
	}

	@Test
	public void onPrepared_OnNewTrackListenerNotSet_exceptionNotThrown() {
		assertThatNPENotThrown(player::onPrepared);
	}
}
