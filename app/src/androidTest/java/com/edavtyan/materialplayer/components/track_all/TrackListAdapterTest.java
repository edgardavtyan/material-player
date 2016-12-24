package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListAdapterTest extends BaseTest {
	private TrackListMvp.Presenter presenter;
	private TrackListViewHolder holder;
	private TrackListAdapter adapter;

	@Override public void beforeEach() {
		super.beforeEach();
		presenter = mock(TrackListMvp.Presenter.class);
		holder = mock(TrackListViewHolder.class);
		adapter = new TrackListAdapter(context, presenter);
	}

	@Test public void onCreateViewHolder_inflateViewWithCorrectParams() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		LinearLayout parent = new LinearLayout(context);
		adapter.onCreateViewHolder(parent, 0);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
	}

	@Test public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		verify(presenter).onBindViewHolder(holder, 0);
	}

	@Test public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}

	@Test public void onHolderClick_callPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onHolderClick(holder);
		verify(presenter).onHolderClick(7);
	}

	@Test public void onMenuAddToPlaylistClick_callPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onMenuAddToPlaylistClick(holder);
		verify(presenter).onAddToPlaylist(7);
	}
}
