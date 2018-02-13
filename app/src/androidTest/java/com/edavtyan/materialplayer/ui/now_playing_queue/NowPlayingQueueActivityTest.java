package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class NowPlayingQueueActivityTest extends ActivityTest {
	private static NowPlayingQueueAdapter adapter;
	private static NowPlayingQueuePresenter presenter;

	public static class TestNowPlayingQueueActivity extends NowPlayingQueueActivity {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.presenter = NowPlayingQueueActivityTest.presenter;
			this.adapter = NowPlayingQueueActivityTest.adapter;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected NowPlayingQueueComponent getComponent() {
			return mock(NowPlayingQueueComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<TestNowPlayingQueueActivity> activityRule
			= new ActivityTestRule<>(TestNowPlayingQueueActivity.class, false, false);

	private TestNowPlayingQueueActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(NowPlayingQueueAdapter.class);
		presenter = mock(NowPlayingQueuePresenter.class);

		activity = activityRule.launchActivity(null);
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		//assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_playlist);
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
