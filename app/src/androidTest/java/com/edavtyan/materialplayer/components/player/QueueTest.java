package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class QueueTest extends BaseTest {
	private PlayerMvp.Queue queue;
	private List innerList;

	@Override
	public void beforeEach() {
		super.beforeEach();
		innerList = mock(List.class);
		queue = spy(new Queue(innerList));
	}

	@Test
	public void addTrack_addTrackToInnerList() {
		Track track = mock(Track.class);
		queue.addTrack(track);
		verify(innerList).add(track);
	}

	@Test
	public void addManyTracks_addTracksToInnerList() {
		List tracks = mock(List.class);
		queue.addManyTracks(tracks);
		verify(innerList).addAll(tracks);
	}

	@Test
	public void removeTrackAt_removeTrackFromInnerQueue() {
		queue.removeTrackAt(1);
		verify(innerList).remove(1);
	}

	@Test
	public void clear_clearInnerList() {
		queue.clear();
		verify(innerList).clear();
	}

	@Test
	public void getTrackAt_returnTrackFromInnerQueue() {
		Track track = mock(Track.class);
		when(innerList.get(2)).thenReturn(track);
		assertThat(queue.getTrackAt(2)).isSameAs(track);
	}

	@Test
	public void getTracksCount_returnInnerListSize() {
		when(innerList.size()).thenReturn(3);
		assertThat(queue.getTracksCount()).isEqualTo(3);
	}

	@Test
	public void moveToNext_notAtLastTrack_incrementPositionAndSetIsEndedToFalse() {
		when(innerList.size()).thenReturn(4);
		queue.setPosition(2);
		queue.moveToNext();
		assertThat(queue.getPosition()).isEqualTo(3);
		assertThat(queue.isEnded()).isFalse();
	}

	@Test
	public void moveToNext_repeatOne_keepPositionAndSetIsEndedToFalse() {
		when(innerList.size()).thenReturn(4);
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ONE);
		queue.setPosition(3);
		queue.moveToNext();
		assertThat(queue.getPosition()).isEqualTo(3);
		assertThat(queue.isEnded()).isFalse();
	}

	@Test
	public void moveToNext_atLastTrackAndRepeatDisabled_keepPositionAndSetIsEndedToTrue() {
		when(innerList.size()).thenReturn(4);
		when(queue.getRepeatMode()).thenReturn(RepeatMode.DISABLED);
		queue.setPosition(3);
		queue.moveToNext();
		assertThat(queue.getPosition()).isEqualTo(3);
		assertThat(queue.isEnded()).isTrue();
	}

	@Test
	public void moveToNext_atLastTrackAndRepeatAll_setPositionToZero() {
		when(innerList.size()).thenReturn(4);
		when(queue.getRepeatMode()).thenReturn(RepeatMode.REPEAT_ALL);
		queue.setPosition(3);
		queue.moveToNext();
		assertThat(queue.getPosition()).isZero();
		assertThat(queue.isEnded()).isFalse();
	}

	@Test
	public void moveToPrev_atFirstTrack_setPositionToLastTrack() {
		when(innerList.size()).thenReturn(5);
		queue.setPosition(0);
		queue.moveToPrev();
		assertThat(queue.getPosition()).isEqualTo(4);
	}

	@Test
	public void moveToPrev_inMiddleAndRepeatDisabled_decrementPosition() {
		testMoveToPrevWithRepeatMode(RepeatMode.DISABLED, 2);
	}

	@Test
	public void moveToPrev_inMiddleAndRepeatAll_decrementPosition() {
		testMoveToPrevWithRepeatMode(RepeatMode.REPEAT_ALL, 2);
	}

	@Test
	public void moveToPrev_inMiddleAndRepeatOne_keepPositionUnchanged() {
		testMoveToPrevWithRepeatMode(RepeatMode.REPEAT_ONE, 3);
	}

	@Test
	public void getCurrentTrack_returnTrackAtGivenIndex() {
		Track track = mock(Track.class);
		when(innerList.get(6)).thenReturn(track);
		queue.setPosition(6);
		assertThat(queue.getCurrentTrack()).isSameAs(track);
	}

	@Test
	public void getSetShuffleMode_setAndReturnGivenMode() {
		queue.setShuffleMode(ShuffleMode.ENABLED);
		assertThat(queue.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test
	public void getSetRepeatMode_setAndReturnGivenMode() {
		queue.setRepeatMode(RepeatMode.REPEAT_ALL);
		assertThat(queue.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void toggleShuffleMode_wasDisabled_switchToEnabled() {
		testToggleShuffle(ShuffleMode.DISABLED, ShuffleMode.ENABLED);
	}

	@Test
	public void toggleShuffleMode_wasEnabled_switchToDisabled() {
		testToggleShuffle(ShuffleMode.ENABLED, ShuffleMode.DISABLED);
	}

	@Test
	public void toggleRepeatMode_wasDisabled_switchToRepeatAll() {
		testToggleRepeat(RepeatMode.DISABLED, RepeatMode.REPEAT_ALL);
	}

	@Test
	public void toggleRepeatMode_wasRepeatAll_switchToRepeatOne() {
		testToggleRepeat(RepeatMode.REPEAT_ALL, RepeatMode.REPEAT_ONE);
	}

	@Test
	public void toggleRepeatMode_wasRepeatOne_switchToDisabled() {
		testToggleRepeat(RepeatMode.REPEAT_ONE, RepeatMode.DISABLED);
	}

	@Test
	public void hasData_noDataInInnerList_returnFalse() {
		when(innerList.size()).thenReturn(0);
		assertThat(queue.hasData()).isFalse();
	}

	@Test
	public void hasData_dataInInnerList_returnTrue() {
		when(innerList.size()).thenReturn(1);
		assertThat(queue.hasData()).isTrue();
	}

	private void testMoveToPrevWithRepeatMode(RepeatMode repeatMode, int expectedPosition) {
		when(queue.getRepeatMode()).thenReturn(repeatMode);
		when(innerList.size()).thenReturn(5);
		queue.setPosition(3);
		queue.moveToPrev();
		assertThat(queue.getPosition()).isEqualTo(expectedPosition);
	}

	private void testToggleShuffle(ShuffleMode from, ShuffleMode to) {
		queue.setShuffleMode(from);
		queue.toggleShuffleMode();
		assertThat(queue.getShuffleMode()).isEqualTo(to);
	}

	private void testToggleRepeat(RepeatMode from, RepeatMode to) {
		queue.setRepeatMode(from);
		queue.toggleRepeatMode();
		assertThat(queue.getRepeatMode()).isEqualTo(to);
	}
}
