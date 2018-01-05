package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingQueueAdapterTest extends BaseTest {
	private NowPlayingQueuePresenter presenter;
	private NowPlayingQueueAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(NowPlayingQueuePresenter.class);
		adapter = spy(new NowPlayingQueueAdapter(context, presenter));
	}

	@Test
	public void return_correct_normal_layout_id() {
		assertThat(adapter.getNormalLayoutId()).isEqualTo(R.layout.listitem_track);
	}

	@Test
	public void return_correct_compact_layout_id() {
		assertThat(adapter.getCompactLayoutId()).isEqualTo(R.layout.listitem_track_compact);
	}

	@Test
	public void onCreateViewHolder_inflateViewAsViewType() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		ViewGroup parent = new LinearLayout(context);
		adapter.onCreateViewHolder(parent, R.layout.listitem_track);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(null, 0);
		verify(presenter).onBindViewHolder(null, 0);
	}

	@Test
	public void getItemCount_callPresenter() {
		when(presenter.getItemCount()).thenReturn(7);
		assertThat(adapter.getItemCount()).isEqualTo(7);
		verify(presenter).getItemCount();
	}
}
