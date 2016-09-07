package com.edavtyan.materialplayer.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class ParallaxHeaderListActivityTest extends ActivityTest {

	private static ParallaxHeaderListPresenter presenter;
	private static RecyclerView.Adapter adapter;

	static {
		presenter = mock(ParallaxHeaderListPresenter.class);
		adapter = mock(RecyclerView.Adapter.class);
	}

	public static class TestParallaxHeaderListActivity extends ParallaxHeaderListActivity {
		@Override
		protected void onCreate(@Nullable Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			init(adapter, presenter);
		}
	}

	private TestParallaxHeaderListActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		activity = startActivity(new Intent(context, TestParallaxHeaderListActivity.class));
		runOnUiThread(() -> activity.init(adapter, presenter));
		reset(adapter, presenter);
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
		activity.finish();
		verify(presenter).onDestroy();
	}

	@Test
	public void setHeaderTitle_setTitleViewText() {
		TextView titleView = activity.findView(R.id.title);
		runOnUiThread(() -> activity.setHeaderTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setHeaderInfo_setInfoViewText() {
		TextView infoView = activity.findView(R.id.info);
		runOnUiThread(() -> activity.setHeaderInfo("info"));
		assertThat(infoView.getText()).isEqualTo("info");
	}
}
