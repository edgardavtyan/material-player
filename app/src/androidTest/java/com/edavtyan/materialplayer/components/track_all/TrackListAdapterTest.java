package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TrackListAdapterTest extends BaseTest {
	private TrackListMvp.Presenter presenter;
	private TrackListViewHolder holder;
	private TrackListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(TrackListMvp.Presenter.class);
		holder = mock(TrackListViewHolder.class);
		adapter = new TrackListAdapter(context, presenter);
	}

	@Test
	public void onCreateViewHolder_inflateViewWithCorrectParams() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		ViewGroup parent = new LinearLayout(context);
		TrackListViewHolder holder = adapter.onCreateViewHolder(parent, 0);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
		assertThat(holder).isNotNull();
	}

	@Test
	public void onBindViewHolder_setViewHolderListeners() {
		adapter.onBindViewHolder(holder, 0);
		verify(holder).setOnHolderClickListener(adapter);
		verify(holder).setOnHolderMenuItemClickListener(adapter);
		verifyNoMoreInteractions(holder);
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		verify(presenter).bindViewHolder(holder, 0);
		verifyNoMoreInteractions(presenter);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
		verifyNoMoreInteractions(presenter);
	}

	@Test
	public void onHolderClick_callPresenter() {
		adapter.onHolderClick(holder);
		verify(presenter).onHolderClick(holder.getAdapterPosition());
		verifyNoMoreInteractions(presenter);
	}

	@Test
	public void onAddToPlaylistMenuItemClick_callPresenter() {
		adapter.onAddToPlaylistMenuItemClick(holder);
		verify(presenter).onAddToPlaylist(holder.getAdapterPosition());
		verifyNoMoreInteractions(presenter);
	}
}
