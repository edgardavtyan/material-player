package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListPresenterTest extends BaseTest {
	private AlbumListModel model;
	private AlbumListView view;
	private AlbumListViewHolder holder;
	private AlbumListPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		model = mock(AlbumListModel.class);
		view = mock(AlbumListView.class);
		holder = mock(AlbumListViewHolder.class);
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

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setTitle("title");
		verify(holder).setInfo(3, "artist");
		verify(holder).setArt("file");
	}

	@Test
	public void getItemCount_returnAlbumsCountFromModel() {
		when(model.getAlbumsCount()).thenReturn(10);
		assertThat(presenter.getItemCount()).isEqualTo(model.getAlbumsCount());
	}

	@Test
	public void onHolderClick_gotoAlbumDetailNormal() {
		Album album = new Album();
		album.setId(7);
		when(model.getAlbumAtIndex(3)).thenReturn(album);

		SourceSharedViews sharedViews = mock(SourceSharedViews.class);

		presenter.onHolderClick(3, sharedViews);
		verify(view).gotoAlbumDetail(7, sharedViews);
	}

	@Test
	public void addToPlaylist_callModel() {
		Album album = new Album();
		album.setId(7);
		when(model.getAlbumAtIndex(3)).thenReturn(album);

		presenter.onAddToPlaylist(3);
		verify(model).addToPlaylist(7);
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
