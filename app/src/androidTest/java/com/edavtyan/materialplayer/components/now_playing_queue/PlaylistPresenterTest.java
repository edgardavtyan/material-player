package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistPresenterTest extends BaseTest {
	private PlaylistMvp.Presenter presenter;
	private PlaylistMvp.Model model;
	private PlaylistMvp.View view;

	@Override public void beforeEach() {
		super.beforeEach();

		model = mock(PlaylistMvp.Model.class);
		view = mock(PlaylistMvp.View.class);
		presenter = new PlaylistPresenter(model, view);
	}

	@Test public void onCreate_bindModel() {
		presenter.onCreate();
		verify(model).bind();
	}

	@Test public void onDestroy_unbindModel() {
		presenter.onDestroy();
		verify(model).unbind();
	}

	@Test public void onItemClick_playItemViaModel() {
		presenter.onItemClick(7);
		verify(model).playItemAtPosition(7);
	}

	@Test public void onRemoveItemClicked_removeItemViaModel() {
		presenter.onRemoveItemClick(7);
		verify(model).removeItemAtPosition(7);
	}

	@Test public void onBindViewHolder_setHolderData() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");
		track.setDuration(657);
		when(model.getTrackAtPosition(7)).thenReturn(track);

		PlaylistViewHolder holder = mock(PlaylistViewHolder.class);
		presenter.onBindViewHolder(holder, 7);

		verify(holder).setTitle("title");
		verify(holder).setInfo(657, "artist", "album");
	}

	@Test public void getItemCount_getItemCountFromModel() {
		when(model.getTrackCount()).thenReturn(7);
		assertThat(presenter.getItemCount()).isEqualTo(7);
		verify(model).getTrackCount();
	}
}
