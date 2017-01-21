package com.edavtyan.materialplayer.components.main;

import android.support.v4.app.FragmentManager;

import com.edavtyan.materialplayer.components.album_all.AlbumListFragment;
import com.edavtyan.materialplayer.components.artist_all.ArtistListFragment;
import com.edavtyan.materialplayer.components.track_all.TrackListFragment;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TextTabsAdapterTest extends BaseTest {
	private TextTabsAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		adapter = new TextTabsAdapter(mock(FragmentManager.class));
	}

	@Test
	public void has_correct_tab_names() {
		assertThat(adapter.getPageTitle(0)).isEqualTo("Artists");
		assertThat(adapter.getPageTitle(1)).isEqualTo("Albums");
		assertThat(adapter.getPageTitle(2)).isEqualTo("Tracks");
	}

	@Test
	public void has_correct_fragments() {
		assertThat(adapter.getItem(0)).isInstanceOf(ArtistListFragment.class);
		assertThat(adapter.getItem(1)).isInstanceOf(AlbumListFragment.class);
		assertThat(adapter.getItem(2)).isInstanceOf(TrackListFragment.class);
		assertThat(adapter.getItem(-1)).isNull();
	}

	@Test
	public void has_correct_number_of_fragments() {
		assertThat(adapter.getCount()).isEqualTo(3);
	}
}
