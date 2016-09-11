package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistActivityTest extends ActivityTest {
	private static PlaylistFactory factory = mock(PlaylistFactory.class);

	public static class TestPlaylistActivity extends PlaylistActivity {
		@Override
		protected PlaylistFactory getFactory() {
			return factory;
		}
	}

	private PlaylistActivity activity;
	private PlaylistAdapter adapter;
	private PlaylistMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(PlaylistAdapter.class);
		presenter = mock(PlaylistMvp.Presenter.class);

		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.providePresenter()).thenReturn(presenter);
		when(app.getPlaylistFactory(any(), any())).thenReturn(factory);

		activity = spy(startActivity(new Intent(new Intent(context, TestPlaylistActivity.class))));
		doNothing().when(activity).baseOnCreate(any());
		doNothing().when(activity).baseOnDestroy();
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_playlist);
	}

	@Test
	public void onCreate_initList() {
		RecyclerView list = activity.findView(R.id.list);

		runOnUiThread(() -> activity.onCreate(null));

		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test
	public void onCreate_initPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onDestroy_closePresenter() {
		activity.onDestroy();
		verify(presenter).onDestroy();
	}
}
