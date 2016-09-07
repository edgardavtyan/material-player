package com.edavtyan.materialplayer.components.track_all;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrackListPresenterTest extends BaseTest {
	private TrackListMvp.Model model;
	private TrackListMvp.View view;
	private TrackListPresenter presenter;

	@Before
	public void beforeEach() {
		super.beforeEach();
		model = mock(TrackListMvp.Model.class);
		view = mock(TrackListMvp.View.class);
		presenter = new TrackListPresenter(view, model);
	}

	@Test
	public void bindViewHolder_setAllHolderValues() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");
		track.setDuration(5000);

		when(model.getTrackAtIndex(0)).thenReturn(track);

		TrackListViewHolder holder = mock(TrackListViewHolder.class);
		presenter.bindViewHolder(holder, 0);

		verify(holder).setTitle(track.getTitle());
		verify(holder).setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Test
	public void bindViewHolder_modelNotUpdated_doNothing() {
		TrackListViewHolder holder = mock(TrackListViewHolder.class);
		presenter.bindViewHolder(holder, 0);
		verifyZeroInteractions(holder);
	}

	@Test
	public void getItemCount_callModel() {
		when(model.getItemCount()).thenReturn(9);
		assertThat(presenter.getItemCount()).isEqualTo(9);
		verify(model).getItemCount();
	}

	@Test
	public void onHolderClick_callViewAndModel() {
		presenter.onHolderClick(0);
		verify(view).goToNowPlaying();
		verify(model).playQueue(0);
	}

	@Test
	public void onAddToPlaylist_callModel() {
		presenter.onAddToPlaylist(0);
		verify(model).addToQueue(0);
	}

	@Test
	public void onCreate_initModel() {
		presenter.onCreate();
		verify(model).bindService();
		verify(model).update();
	}

	@Test
	public void onDestroy_cleanupModel() {
		presenter.onDestroy();
		verify(model).unbindService();
		verify(model).close();
	}
}
