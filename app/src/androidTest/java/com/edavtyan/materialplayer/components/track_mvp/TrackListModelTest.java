package com.edavtyan.materialplayer.components.track_mvp;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.db.TrackDB;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListModelTest extends BaseTest {
	private List tracks;
	private TrackListModel model;
	private MusicPlayerService service;
	private MusicPlayerBinder binder;
	private Context mockContext;

	@Override
	@SuppressWarnings({"unchecked", "WrongConstant"})
	public void beforeEach() {
		super.beforeEach();

		mockContext = mock(Context.class);

		tracks = mock(List.class);
		TrackDB db = mock(TrackDB.class);
		when(db.getAllTracks()).thenReturn(tracks);

		model = spy(new TrackListModel(mockContext, db));
		service = mock(MusicPlayerService.class);
		binder = mock(MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		doAnswer(invocationOnMock -> {
			model.onServiceConnected(null, binder);
			return null;
		}).when(mockContext).bindService(any(), any(), anyInt());
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
	public void getTrackAtIndex_modelNotUpdated_null() {
		assertThat(model.getTrackAtIndex(0)).isNull();
	}

	@Test
	public void getTrackAtIndex_modelClosed_null() {
		model.update();
		model.close();
		assertThat(model.getTrackAtIndex(0)).isNull();
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
		model.bindService();
		model.update();

		model.playQueue(0);

		verify(service).playQueue(tracks, 0);
	}

	@Test
	public void addToQueue_serviceBound_updateService() {
		Track track = new Track();
		when(tracks.get(0)).thenReturn(track);
		model.bindService();
		model.update();

		model.addToQueue(0);

		verify(service).addToQueue(track);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bindService_callContextAndBindService() {
		model.bindService();
		verify(mockContext)
				.bindService(Mockito.isA(Intent.class), eq(model), eq(Context.BIND_AUTO_CREATE));
	}

	@Test
	public void unbindService_callContextAndUnbindService() {
		model.unbindService();
		verify(mockContext).unbindService(model);
	}
}
