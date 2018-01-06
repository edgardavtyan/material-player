package com.edavtyan.materialplayer.components.now_playing_queue;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingQueueActivityTest extends ActivityTest {
	@Rule
	public final ActivityTestRule<NowPlayingActivity> activityRule
			= new ActivityTestRule<>(NowPlayingActivity.class, false, false);

	private NowPlayingQueueActivity activity;
	private NowPlayingQueueAdapter adapter;
	private NowPlayingQueuePresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(NowPlayingQueueAdapter.class);
		presenter = mock(NowPlayingQueuePresenter.class);

		NowPlayingQueueFactory factory = mock(NowPlayingQueueFactory.class);
		when(factory.getAdapter()).thenReturn(adapter);
		when(factory.getPresenter()).thenReturn(presenter);

		app.setNowPlayingQueueFactory(factory);

		activity = startActivity(NowPlayingQueueActivity.class);
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_playlist);
	}

	@Test
	public void onCreate_initList() {
		RecyclerView list = (RecyclerView) activity.findViewById(R.id.list);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test
	public void onCreate_initPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void notify_adapter_of_data_changes() {
		activity.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
