package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListModelTest extends BaseTest {
	private List tracks;
	private TrackListModel model;
	private Player player;
	private PlayerService service;

	@SuppressWarnings({"unchecked", "WrongConstant"})
	@Override
	public void beforeEach() {
		super.beforeEach();

		tracks = mock(List.class);
		TrackDB db = mock(TrackDB.class);
		when(db.getAllTracks()).thenReturn(tracks);

		ModelServiceModule serviceModule = mock(ModelServiceModule.class);

		model = spy(new TrackListModel(serviceModule, db, playlistManager));

		player = mock(Player.class);

		service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void getTrackAtIndex_modelUpdated_trackAtGivenIndex() {
		Track track = new Track();
		when(tracks.get(3)).thenReturn(track);

		model.update();

		assertThat(model.getTrackAtIndex(3)).isSameAs(track);
	}

	@Test
	public void getTrackAtIndex_modelNotUpdated_throwException() {
		assertThatThrownBy(() -> model.getTrackAtIndex(0))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Tracks have not been initialized");
	}

	@Test
	public void getTrackAtIndex_modelClosed_throwException() {
		model.update();
		model.close();
		assertThatThrownBy(() -> model.getTrackAtIndex(0))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Tracks have not been initialized");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void getItemCount_modelUpdated_tracksCount() {
		when(tracks.size()).thenReturn(8);

		model.update();

		assertThat(model.getItemCount()).isEqualTo(8);
	}

	@Test
	public void getItemCount_modelNotUpdated_zero() {
		when(tracks.size()).thenReturn(8);
		assertThat(model.getItemCount()).isZero();
	}

	@Test
	public void getItemCount_modelClosed_zero() {
		when(tracks.size()).thenReturn(8);
		model.update();
		model.close();
		assertThat(model.getItemCount()).isZero();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void playQueue_serviceBound_updateService() {
		model.onServiceConnected(service);
		model.update();

		model.playQueue(0);

		verify(player).playNewTracks(tracks, 0);
	}

	@Test
	public void addToQueue_serviceBound_updateService() {
		Track track = new Track();
		when(tracks.get(0)).thenReturn(track);
		model.onServiceConnected(service);
		model.update();

		model.addToQueue(0);

		verify(player).addTrack(track);
	}
}
