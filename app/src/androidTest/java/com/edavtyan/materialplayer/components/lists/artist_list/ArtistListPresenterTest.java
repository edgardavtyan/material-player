package com.edavtyan.materialplayer.components.lists.artist_list;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListPresenterTest extends BaseTest {
	private ArtistListModel model;
	private ArtistListView view;
	private ArtistListViewHolder holder;
	private ArtistListPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		model = mock(ArtistListModel.class);
		view = mock(ArtistListView.class);
		holder = mock(ArtistListViewHolder.class);
		presenter = new ArtistListPresenter(model, view);
	}

	@Test
	public void bindViewHolder_setHolderTitleAndInfo() {
		Artist artist = new Artist();
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(11);

		when(model.getArtistAtIndex(0)).thenReturn(artist);

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setTitle(artist.getTitle());
		verify(holder).setInfo(artist.getAlbumsCount(), artist.getTracksCount());
	}

	@Test
	public void bindViewHolder_setImageViewArtViaCallback() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);

		doAnswer(invocation -> {
			ArtistListImageTask.Callback callback = (ArtistListImageTask.Callback) invocation.getArguments()[1];
			callback.onArtLoaded(art);
			return null;
		}).when(model).getArtistImage(eq(0), any());
		doReturn(new Artist()).when(model).getArtistAtIndex(0);

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setImage(art);
	}

	@Test
	public void getItemCount_returnCountFromModel() {
		when(model.getArtistCount()).thenReturn(5);
		assertThat(presenter.getItemCount()).isEqualTo(5);
	}

	@Test
	public void onHolderClick_compactModeEnabled_gotoArtistDetailCompact() {
		Artist artist = new Artist();
		artist.setTitle("title");
		when(model.getArtistAtIndex(3)).thenReturn(artist);
		when(model.isCompactModeEnabled()).thenReturn(true);

		presenter.onHolderClick(3);
		verify(view).gotoArtistDetailCompact("title");
	}

	@Test
	public void onHolderClick_compactModeDisabled_gotoArtistDetailNormal() {
		Artist artist = new Artist();
		artist.setTitle("title");
		when(model.getArtistAtIndex(3)).thenReturn(artist);
		when(model.isCompactModeEnabled()).thenReturn(false);

		presenter.onHolderClick(3);
		verify(view).gotoArtistDetailNormal("title");
	}

	@Test
	public void onCreate_initModel() {
		presenter.onCreate();
		verify(model).update();
	}

	@Test
	public void onDestroy_doesNothing() {
		presenter.onDestroy();
	}
}
