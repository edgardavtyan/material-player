package com.edavtyan.materialplayer.components.player;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.player.PlayerMvp.Player.OnNewTrackListener;
import com.edavtyan.materialplayer.components.player.PlayerMvp.Player.OnPlayPauseListener;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class PlayerTest extends BaseTest {
	private AdvancedSharedPrefs basePrefs;
	private PlayerPrefs prefs;
	private PlayerMvp.AudioEngine audioEngine;
	private PlayerMvp.Queue queue;
	private PlayerMvp.Player player;

	@Override public void beforeEach() {
		super.beforeEach();
		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		prefs = new PlayerPrefs(basePrefs);
		audioEngine = mock(PlayerMvp.AudioEngine.class);
		queue = mock(PlayerMvp.Queue.class);
		player = new Player(audioEngine, queue, prefs);
	}

	@Override public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test public void constructor_setDefaultShuffleMode() {
		verify(queue).setShuffleMode(ShuffleMode.DISABLED);
	}

	@Test public void constructor_setDefaultRepeatMode() {
		verify(queue).setRepeatMode(RepeatMode.DISABLED);
	}

	@Test public void constructor_setShuffleModeFromPrefs() {
		prefs.saveShuffleMode(ShuffleMode.ENABLED);
		player = new Player(audioEngine, queue, prefs);
		verify(queue).setShuffleMode(ShuffleMode.ENABLED);
	}

	@Test public void constructor_setRepeatModeFromPrefs() {
		prefs.saveRepeatMode(RepeatMode.REPEAT_ALL);
		player = new Player(audioEngine, queue, prefs);
		verify(queue).setRepeatMode(RepeatMode.REPEAT_ALL);
	}

	@Test public void addTrack_addTrackToQueue() {
		Track track = mock(Track.class);
		player.addTrack(track);
		verify(queue).addTrack(track);
	}

	@Test public void addManyTracks_addTracksToQueue() {
		List tracks = mock(List.class);
		player.addManyTracks(tracks);
		verify(queue).addManyTracks(tracks);
	}

	@Test public void removeTrackAt_removeTrackFromQueue() {
		player.removeTrackAt(9);
		verify(queue).removeTrackAt(9);
	}

	@Test public void getTrackAt_returnTrackFromQueue() {
		Track track = mock(Track.class);
		when(queue.getTrackAt(7)).thenReturn(track);
		assertThat(player.getTrackAt(7)).isSameAs(track);
	}

	@Test public void getTracksCount_returnCountFromQueue() {
		when(queue.getTracksCount()).thenReturn(15);
		assertThat(player.getTracksCount()).isEqualTo(15);
	}

	@Test public void playNewTracks_addTracksToQueueAndPlaySelected() {
		List tracks = mock(List.class);
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);

		player.playNewTracks(tracks, 6);

		InOrder order = inOrder(queue, audioEngine);
		order.verify(queue).clear();
		order.verify(queue).addManyTracks(tracks);
		order.verify(queue).setPosition(6);
		order.verify(audioEngine).playTrack("path");
	}

	@Test public void playTrackAt_playTrackAtGivenPosition() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);

		player.playTrackAt(4);

		InOrder order = inOrder(queue, audioEngine);
		order.verify(queue).setPosition(4);
		order.verify(audioEngine).playTrack("path");
	}

	@Test public void play_playAudioEngine() {
		player.play();
		verify(audioEngine).play();
	}

	@Test public void pause_pauseAudioEngine() {
		player.pause();
		verify(audioEngine).pause();
	}

	@Test public void playNext_moveQueueToNextAndStartEngine() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);
		when(queue.hasData()).thenReturn(true);

		player.playNext();

		InOrder order = inOrder(queue, audioEngine);
		order.verify(queue).moveToNext();
		order.verify(audioEngine).playTrack("path");
	}

	@Test public void playNext_queueEnded_notPlayAnything() {
		when(queue.hasData()).thenReturn(true);
		when(queue.isEnded()).thenReturn(true);
		player.playNext();
		verify(audioEngine, never()).playTrack(any());
	}

	@Test public void rewind_playedLessThatFiveSeconds_playPreviousTrack() {
		Track track = mock(Track.class);
		when(track.getPath()).thenReturn("path");
		when(queue.getCurrentTrack()).thenReturn(track);
		when(audioEngine.getPosition()).thenReturn(4999L);

		player.rewind();

		verify(queue).moveToPrev();
		verify(audioEngine).playTrack("path");
	}

	@Test public void rewind_playedFiveSecondsOrMore_playFromBeginning() {
		when(audioEngine.getPosition()).thenReturn(5000L);
		player.rewind();
		verify(audioEngine).setPosition(0);
	}

	@Test public void getShuffleMode_returnModeFromQueue() {
		when(queue.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		assertThat(player.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test public void toggleShuffleMode_callQueue() {
		player.toggleShuffleMode();
		verify(queue).toggleShuffleMode();
	}

	@Test public void toggleShuffleMode_saveShuffleMode() {
		when(queue.getShuffleMode()).thenReturn(ShuffleMode.ENABLED);
		player.toggleShuffleMode();
		assertThat(prefs.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test public void getRepeatMode_returnModeFromQueue() {
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		assertThat(player.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test public void toggleRepeatMode_callQueue() {
		player.toggleRepeatMode();
		verify(queue).toggleRepeatMode();
	}

	@Test public void toggleRepeatMode_saveRepeatMode() {
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		player.toggleRepeatMode();
		assertThat(prefs.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test public void getCurrentTrack_returnTrackFromQueue() {
		Track track = mock(Track.class);
		when(queue.getCurrentTrack()).thenReturn(track);
		assertThat(player.getCurrentTrack()).isSameAs(track);
	}

	@Test public void isPlaying_returnFromEngine() {
		when(audioEngine.isPlaying()).thenReturn(true);
		assertThat(player.isPlaying()).isEqualTo(true);
	}

	@Test public void playPause_callEngine() {
		player.playPause();
		verify(audioEngine).playPause();
	}

	@Test public void playPause_callOnPlayPauseListener() {
		OnPlayPauseListener listener = mock(OnPlayPauseListener.class);
		player.setOnPlayPauseListener(listener);
		player.playPause();
		verify(listener).onPlayPause();
	}

	@Test public void hasData_returnFromQueue() {
		when(queue.hasData()).thenReturn(true);
		assertThat(player.hasData()).isTrue();
	}

	@Test public void getPosition_returnPositionFromEngine() {
		when(audioEngine.getPosition()).thenReturn(120L);
		assertThat(player.getPosition()).isEqualTo(120L);
	}

	@Test public void setPosition_callEngine() {
		player.setPosition(60);
		verify(audioEngine).setPosition(60);
	}

	@Test public void lowerVolume_setAudioEngineVolume() {
		player.lowerVolume();
		verify(audioEngine).setVolume(0.3f);
	}

	@Test public void restoreVolume_setAudioEngineVolume() {
		player.restoreVolume();
		verify(audioEngine).setVolume(1.0f);
	}

	@Test public void onPrepared_OnNewTrackListenerSet_callOnNewTrackListener() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		player.setOnNewTrackListener(listener);
		player.onPrepared();
		verify(listener).onNewTrack();
	}

	@Test public void onPrepared_OnNewTrackListenerNotSet_exceptionNotThrown() {
		try {
			player.onPrepared();
		} catch (NullPointerException e) {
			fail("OnNewTrackListener should not throw exception if not set");
		}
	}
}
