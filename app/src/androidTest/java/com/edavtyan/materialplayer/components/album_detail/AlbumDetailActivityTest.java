package com.edavtyan.materialplayer.components.album_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityTest extends ActivityTest {
	private static AlbumDetailFactory factory = mock(AlbumDetailFactory.class);

	public static class TestAlbumDetailActivity extends AlbumDetailActivity {
		@Override
		protected AlbumDetailFactory getDI() {
			return factory;
		}
	}

	private static AlbumDetailMvp.Presenter presenter;
	private static AlbumDetailAdapter adapter;
	private static TestAlbumDetailActivity activity;
	private static Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(AlbumDetailMvp.Presenter.class);
			adapter = mock(AlbumDetailAdapter.class);
			navigator = mock(Navigator.class);

			when(factory.providePresenter()).thenReturn(presenter);
			when(factory.provideAdapter()).thenReturn(adapter);
			when(factory.provideNavigator()).thenReturn(navigator);

			activity = startActivity(new Intent(context, TestAlbumDetailActivity.class));
		} else {
			reset(adapter, presenter, navigator);
		}
	}

	@Test
	public void onCreate_initList() {
		RecyclerView list = activity.findView(R.id.list);
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
	public void setAlbumTitle_setTitleViewText() {
		TextView titleView = activity.findView(R.id.title);
		runOnUiThread(() -> activity.setAlbumTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setAlbumInfo_setInfoTextWithPattern() {
		TextView infoView = activity.findView(R.id.info);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 9));
		assertThat(infoView.getText()).isEqualTo("artist | 9 Tracks");
	}

	@Test
	public void goToNowPlaying_callNavigator() {
		runOnUiThread(() -> activity.goToNowPlaying());
		verify(navigator).gotoNowPlaying();
	}
}
