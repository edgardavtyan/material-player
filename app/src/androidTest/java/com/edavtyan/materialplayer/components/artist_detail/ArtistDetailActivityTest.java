package com.edavtyan.materialplayer.components.artist_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityTest extends ActivityTest {
	private static ArtistDetailFactory factory = mock(ArtistDetailFactory.class);
	private static ArtistDetailMvp.Presenter presenter;
	private static AlbumListAdapter adapter;
	private static TestArtistDetailActivity activity;
	private static Navigator navigator;

	public static class TestArtistDetailActivity extends ArtistDetailActivity {
		@Override
		protected ArtistDetailFactory getDI() {
			return factory;
		}
	}

	@Override public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(ArtistDetailMvp.Presenter.class);
			adapter = mock(AlbumListAdapter.class);
			navigator = mock(Navigator.class);

			when(factory.providePresenter()).thenReturn(presenter);
			when(factory.provideAdapter()).thenReturn(adapter);
			when(factory.provideNavigator()).thenReturn(navigator);

			activity = spy(startActivity(new Intent(context, TestArtistDetailActivity.class)));
			doNothing().when(activity).baseOnStop();
		} else {
			reset(adapter, presenter, navigator);
		}
	}

	@Test public void onCreate_initList() {
		RecyclerView list = activity.findView(R.id.list);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test public void onStart_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test public void onStop_callPresenter() {
		instrumentation.callActivityOnStop(activity);
		verify(presenter).onDestroy();
	}

	@Test public void setArtistTitle_setTitleViewText() {
		runOnUiThread(() -> {
			TextView titleView = activity.findView(R.id.title);
			activity.setArtistTitle("title");
			assertThat(titleView.getText()).isEqualTo("title");
		});
	}

	@Test public void setArtistInfo_setInfoViewTextWithPattern() {
		runOnUiThread(() -> {
			TextView infoView = activity.findView(R.id.info);
			activity.setArtistInfo(3, 9);
			assertThat(infoView.getText()).isEqualTo("3 Albums | 9 Tracks");
		});
	}

	@Test public void goToAlbumDetail_callNavigator() {
		activity.goToAlbumDetail(3);
		verify(navigator).gotoAlbumDetail(3);
	}
}
