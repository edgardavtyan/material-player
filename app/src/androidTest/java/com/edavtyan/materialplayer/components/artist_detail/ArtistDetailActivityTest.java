package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailActivityTest extends ActivityTest {

	private static ArtistDetailFactory factory = mock(ArtistDetailFactory.class);
	private ArtistDetailMvp.Presenter presenter;
	private AlbumListAdapter adapter;
	private TestArtistDetailActivity activity;
	private Navigator navigator;

	public static class TestArtistDetailActivity extends ArtistDetailActivity {
		@Override
		protected ArtistDetailFactory getDI() {
			return factory;
		}
	}

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(ArtistDetailMvp.Presenter.class);
		adapter = mock(AlbumListAdapter.class);
		navigator = mock(Navigator.class);

		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.provideNavigator()).thenReturn(navigator);

		activity = startActivity(new Intent(context, TestArtistDetailActivity.class));
	}

	@Test
	public void onCreate_initRecyclerView() {
		RecyclerView list = (RecyclerView) activity.findViewById(R.id.list);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test
	public void onStart_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		instrumentation.callActivityOnStop(activity);
		verify(presenter).onDestroy();
	}

	@Test
	public void setArtistTitle_setTitleViewText() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setArtistTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setArtistInfo_setInfoViewTextWithPattern() {
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> activity.setArtistInfo(3, 9));
		assertThat(infoView.getText()).isEqualTo("3 Albums | 9 Tracks");
	}

	@Test
	public void goToAlbumDetail_callNavigator() {
		runOnUiThread(() -> activity.goToAlbumDetail(3));
		verify(navigator).gotoAlbumDetail(3);
	}
}
