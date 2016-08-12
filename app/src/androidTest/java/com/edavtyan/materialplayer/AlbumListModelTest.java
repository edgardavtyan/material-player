package com.edavtyan.materialplayer;

import com.edavtyan.materialplayer.components.album_mvp.AlbumDB;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListModel;
import com.edavtyan.materialplayer.components.album_mvp.TrackDB;
import com.edavtyan.materialplayer.components.albums.Album;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
import com.edavtyan.materialplayer.components.tracks.Track;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
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
	private static List<Album> albums;
	private static List<Track> tracks;
	private AlbumDB albumDB;
	private TrackDB trackDB;
	private AlbumListModel model;
	private MusicPlayerService service;

	@BeforeClass
	public static void beforeClass() {
		albums = new ArrayList<>();
		for (int i = 0; i < 10; i++) albums.add(new Album());

		tracks = new ArrayList<>();
		for (int i = 0; i < 10; i++) tracks.add(new Track());
	}

	@Override
	@SuppressWarnings("WrongConstant")
	public void beforeEach() {
		super.beforeEach();
		albumDB = mock(AlbumDB.class);
		trackDB = mock(TrackDB.class);
		model = new AlbumListModel(context, albumDB, trackDB);
		service = mock(MusicPlayerService.class);

		MusicPlayerService.MusicPlayerBinder binder = mock(MusicPlayerService.MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		model = new AlbumListModel(context, albumDB, trackDB);
		doAnswer(invocationOnMock -> {
			model.onServiceConnected(null, binder);
			return null;
		}).when(context).bindService(any(), any(), anyInt());
	}

	@Test
	public void getAlbumAtIndex_dataNotUpdated_null() {
		when(albumDB.getAllAlbums()).thenReturn(albums);
		assertThat(model.getAlbumAtIndex(0)).isNull();
	}

	@Test
	public void getAlbumAtIndex_dataUpdated_correctAlbum() {
		when(albumDB.getAllAlbums()).thenReturn(albums);
		model.update();
		assertThat(model.getAlbumAtIndex(2)).isSameAs(albums.get(2));
	}

	@Test
	public void getAlbumsCount_dataNotUpdated_zero() {
		when(albumDB.getAllAlbums()).thenReturn(albums);
		assertThat(model.getAlbumsCount()).isEqualTo(0);
	}

	@Test
	public void getAlbumsCount_dataUpdated_correctCount() {
		when(albumDB.getAllAlbums()).thenReturn(albums);
		model.update();
		assertThat(model.getAlbumsCount()).isEqualTo(10);
	}

	@Test
	public void addToPlaylist_bindServiceCalled_callService() {
		NowPlayingQueue queue = mock(NowPlayingQueue.class);
		when(service.getQueue()).thenReturn(queue);
		when(trackDB.getTracksWithAlbumId(0)).thenReturn(tracks);

		model.bindService();
		model.addToPlaylist(0);

		verify(queue).addAll(any());
	}

	@Test
	public void addToPlaylist_bindServiceNotCalled_throwException() {
		NowPlayingQueue queue = mock(NowPlayingQueue.class);
		when(service.getQueue()).thenReturn(queue);
		when(trackDB.getTracksWithAlbumId(0)).thenReturn(tracks);

		assertThatThrownBy(() -> model.addToPlaylist(0))
				.isInstanceOf(IllegalStateException.class);
	}
}
