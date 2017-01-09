package com.edavtyan.materialplayer.components.now_playing_queue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingQueueActivityTest extends ActivityTest {
	private static NowPlayingQueueActivity activity;
	private static NowPlayingQueueAdapter adapter;
	private static NowPlayingQueueMvp.Presenter presenter;

	@Override public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			adapter = mock(NowPlayingQueueAdapter.class);
			presenter = mock(NowPlayingQueueMvp.Presenter.class);

			NowPlayingQueueFactory factory = mock(NowPlayingQueueFactory.class);
			when(factory.provideAdapter()).thenReturn(adapter);
			when(factory.providePresenter()).thenReturn(presenter);
			when(app.getPlaylistFactory(any(), any())).thenReturn(factory);

			activity = spy(startActivity(new Intent(context, NowPlayingQueueActivity.class)));
			doNothing().when(activity).baseOnCreate(any());
			doNothing().when(activity).baseOnDestroy();
			doReturn(app).when(activity).getApplicationContext();
		} else {
			reset(adapter, presenter);
		}
	}

	@Test public void getLayoutId_returnCorrectId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_playlist);
	}

	@Test public void onCreate_initList() {
		runOnUiThread(() -> {
			RecyclerView list = new RecyclerView(context);
			when(activity.findView(R.id.list)).thenReturn(list);
			activity.onCreate(null);

			assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
			assertThat(list.getAdapter()).isEqualTo(adapter);
		});
	}

	@Test public void onCreate_initPresenter() {
		runOnUiThread(() -> {
			activity.onCreate(null);
			verify(presenter).onCreate();
		});
	}

	@Test public void onDestroy_closePresenter() {
		activity.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test public void notify_adapter_of_data_changes() {
		activity.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
