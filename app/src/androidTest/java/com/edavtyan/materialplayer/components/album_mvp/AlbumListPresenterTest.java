package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class AlbumListPresenterTest extends BaseTest {

	private AlbumListViewHolder holder;
	private AlbumListMvp.Model model;
	private AlbumListMvp.View view;
	private AlbumListPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		holder = mock(AlbumListViewHolder.class);
		model = mock(AlbumListMvp.Model.class);
		view = mock(AlbumListMvp.View.class);
		presenter = new AlbumListPresenter(model, view);
	}

	@Test
	public void bindViewHolder_setAllHolderValues() {
		Album album = new Album();
		album.setId(0);
		album.setTitle("title");
		album.setTracksCount(3);
		album.setArtistTitle("artist");
		album.setArt("file");

		when(model.getAlbumAtIndex(0)).thenReturn(album);

		presenter.bindViewHolder(holder, 0);

		verify(holder).setAlbumId(0);
		verify(holder).setTitle("title");
		verify(holder).setInfo(3, "artist");
		verify(holder).setArt("file");
		verifyNoMoreInteractions(holder);
	}

	@Test
	public void getItemCount_albumsCountFromModel() {
		when(model.getAlbumsCount()).thenReturn(10);
		assertThat(presenter.getItemCount()).isEqualTo(model.getAlbumsCount());
	}

	@Test
	public void onItemClick_goToAlbumDetail() {
		presenter.onItemClicked(0);
		verify(view).goToAlbumDetail(0);
	}

	@Test
	public void addToPlaylist_callModel() {
		presenter.addToPlaylist(0);
		verify(model).addToPlaylist(0);
	}

	@Test
	public void onCreate_prepareModel() {
		presenter.onCreate();
		verify(model).update();
		verify(model).bindService();
	}

	@Test
	public void onDestroy_releaseModel() {
		presenter.onDestroy();
		verify(model).unbindService();
	}
}
