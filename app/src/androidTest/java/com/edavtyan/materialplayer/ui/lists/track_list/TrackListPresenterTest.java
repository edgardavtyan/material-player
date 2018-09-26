package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListPresenterTest extends BaseTest {
	private TrackListModel model;
	private TrackListView view;
	private TrackListViewHolder holder;
	private TrackListPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		model = mock(TrackListModel.class);
		view = mock(TrackListView.class);
		holder = mock(TrackListViewHolder.class);
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

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setTitle(track.getTitle());
		verify(holder).setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Test
	public void getItemCount_callModel() {
		when(model.getItemCount()).thenReturn(9);
		assertThat(presenter.getItemCount()).isEqualTo(9);
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
