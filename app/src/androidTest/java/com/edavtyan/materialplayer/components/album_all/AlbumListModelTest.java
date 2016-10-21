package com.edavtyan.materialplayer.components.album_all;

import com.edavtyan.materialplayer.components.player2.PlayerMvp;
import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListModelTest extends BaseTest {
	private static List albums;
	private AlbumListModel model;
	private PlayerService service;

	@SuppressWarnings({"WrongConstant", "unchecked"})
	@Override public void beforeEach() {
		super.beforeEach();

		service = mock(PlayerService.class);
		PlayerService.PlayerBinder binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		albums = mock(List.class);
		AlbumDB albumDB = mock(AlbumDB.class);
		when(albumDB.getAllAlbums()).thenReturn(albums);

		TrackDB trackDB = mock(TrackDB.class);

		model = new AlbumListModel(context, albumDB, trackDB);
		doAnswer(invocationOnMock -> {
			model.onServiceConnected(null, binder);
			return null;
		}).when(context).bindService(any(), any(), anyInt());
	}

	@Test public void getAlbumAtIndex_dataNotUpdated_null() {
		assertThat(model.getAlbumAtIndex(0)).isNull();
	}

	@Test public void getAlbumAtIndex_dataUpdated_correctAlbum() {
		Album album = mock(Album.class);
		when(albums.get(2)).thenReturn(album);

		model.update();

		assertThat(model.getAlbumAtIndex(2)).isSameAs(albums.get(2));
	}

	@Test public void getAlbumsCount_dataNotUpdated_zero() {
		assertThat(model.getAlbumsCount()).isEqualTo(0);
	}

	@Test public void getAlbumsCount_dataUpdated_correctCount() {
		when(albums.size()).thenReturn(10);

		model.update();

		assertThat(model.getAlbumsCount()).isEqualTo(10);
	}

	@Test public void addToPlaylist_bindServiceCalled_addTracksToPlayer() {
		PlayerMvp.Player player = mock(PlayerMvp.Player.class);
		when(service.getPlayer()).thenReturn(player);

		model.bindService();
		model.addToPlaylist(0);

		verify(player).addManyTracks(any());
	}

	@Test public void addToPlaylist_bindServiceNotCalled_throwException() {
		assertThatThrownBy(() -> model.addToPlaylist(0))
				.isInstanceOf(IllegalStateException.class);
	}
}
