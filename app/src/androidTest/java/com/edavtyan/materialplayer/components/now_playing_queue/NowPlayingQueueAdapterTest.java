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
	private NowPlayingQueueMvp.Presenter presenter;
	private NowPlayingQueueViewHolder holder;
	private NowPlayingQueueAdapter adapter;

	@Override public void beforeEach() {
		super.beforeEach();
		presenter = mock(NowPlayingQueueMvp.Presenter.class);
		holder = mock(NowPlayingQueueViewHolder.class);
		adapter = spy(new NowPlayingQueueAdapter(context, presenter));
	}

	@Test public void onCreateViewHolder_inflateViewAsViewType() {
		ViewGroup parent = new LinearLayout(context);
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		adapter.onCreateViewHolder(parent, R.layout.listitem_track);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
	}

	@Test public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 7);
		verify(presenter).onBindViewHolder(holder, 7);
	}

	@Test public void getItemCount_callPresenter() {
		when(presenter.getItemCount()).thenReturn(7);
		assertThat(adapter.getItemCount()).isEqualTo(7);
		verify(presenter).getItemCount();
	}

	@Test public void onHolderClick_playClickedTrackViaPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onHolderClick(holder);
		verify(presenter).onItemClick(7);
	}

	@Test public void onRemoveFromQueueClick_removeTrackFromQueueViaPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onRemoveFromQueueClick(holder);
		verify(presenter).onRemoveItemClick(7);
		verify(adapter).notifyItemRemovedNonFinal(7);
	}
}
