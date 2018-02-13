package com.edavtyan.materialplayer.ui.lists.track_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListAdapterTest extends BaseTest {
	private TrackListPresenter presenter;
	private TrackListViewHolder holder;
	private TrackListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(TrackListPresenter.class);
		holder = mock(TrackListViewHolder.class);
		adapter = new TrackListAdapter(context, presenter, new SdkFactory());
	}

	@Test
	public void onCreateViewHolder_inflateViewAsViewType() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		LinearLayout parent = new LinearLayout(context);
		adapter.onCreateViewHolder(parent, R.layout.listitem_track);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		//noinspection RedundantCast
		((ListPresenter<TrackListViewHolder>)verify(presenter)).onBindViewHolder(holder, 0);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}
}
