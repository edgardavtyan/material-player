package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
import com.edavtyan.materialplayer.lib.BaseTest;

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
	private static List tracks;
	private AlbumDB albumDB;
	private TrackDB trackDB;
	private AlbumListModel model;
	private MusicPlayerService service;

	@Override
	@SuppressWarnings({"WrongConstant", "unchecked"})
	public void beforeEach() {
		super.beforeEach();

		albums = mock(List.class);
		albumDB = mock(AlbumDB.class);
		when(albumDB.getAllAlbums()).thenReturn(albums);

		tracks = mock(List.class);
		trackDB = mock(TrackDB.class);
		when(trackDB.getAllTracks()).thenReturn(tracks);

		model = new AlbumListModel(context, albumDB, trackDB);
		service = mock(MusicPlayerService.class);

		MusicPlayerService.MusicPlayerBinder binder
				= mock(MusicPlayerService.MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		model = new AlbumListModel(context, albumDB, trackDB);
		doAnswer(invocationOnMock -> {
			model.onServiceConnected(null, binder);
			return null;
		}).when(context).bindService(any(), any(), anyInt());
	}

	@Test
	public void getAlbumAtIndex_dataNotUpdated_null() {
		assertThat(model.getAlbumAtIndex(0)).isNull();
	}

	@Test
	public void getAlbumAtIndex_dataUpdated_correctAlbum() {
		Album album = mock(Album.class);
		when(albums.get(2)).thenReturn(album);

		model.update();

		assertThat(model.getAlbumAtIndex(2)).isSameAs(albums.get(2));
	}

	@Test
	public void getAlbumsCount_dataNotUpdated_zero() {
		assertThat(model.getAlbumsCount()).isEqualTo(0);
	}

	@Test
	public void getAlbumsCount_dataUpdated_correctCount() {
		when(albums.size()).thenReturn(10);

		model.update();

		assertThat(model.getAlbumsCount()).isEqualTo(10);
	}

	@Test
	public void addToPlaylist_bindServiceCalled_callService() {
		NowPlayingQueue queue = mock(NowPlayingQueue.class);
		when(service.getQueue()).thenReturn(queue);

		model.bindService();
		model.addToPlaylist(0);

		verify(queue).addAll(any());
	}

	@Test
	public void addToPlaylist_bindServiceNotCalled_throwException() {
		NowPlayingQueue queue = mock(NowPlayingQueue.class);
		when(service.getQueue()).thenReturn(queue);

		assertThatThrownBy(() -> model.addToPlaylist(0))
				.isInstanceOf(IllegalStateException.class);
	}
}
