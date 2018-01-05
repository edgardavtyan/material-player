package com.edavtyan.materialplayer.mvp.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListAdapter;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListAdapterTest extends BaseTest {
	private ListAdapter listAdapter;
	private ListMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(ListMvp.Presenter.class);
		listAdapter = new TestListAdapter(context, presenter);
	}

	@Test
	public void inflate_view_type_as_layout() {
		LayoutInflater inflater = mock(LayoutInflater.class);
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);
		ViewGroup parent = mock(ViewGroup.class);

		listAdapter.onCreateViewHolder(parent, R.layout.listitem_track);

		verify(inflater).inflate(R.layout.listitem_track, parent, false);
	}

	@Test
	public void return_normal_layout_if_compact_mode_disabled() {
		when(presenter.isCompactModeEnabled()).thenReturn(false);
		assertThat(listAdapter.getItemViewType(0)).isEqualTo(R.layout.listitem_track);
	}

	@Test
	public void return_compact_layout_if_compact_mode_enabled() {
		when(presenter.isCompactModeEnabled()).thenReturn(true);
		assertThat(listAdapter.getItemViewType(0)).isEqualTo(R.layout.listitem_track_compact);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void bind_view_holder_via_presenter() {
		RecyclerView.ViewHolder holder = mock(RecyclerView.ViewHolder.class);
		listAdapter.onBindViewHolder(holder, 0);
		verify(presenter).onBindViewHolder(holder, 0);
	}

	@Test
	public void get_item_count_from_presenter() {
		when(presenter.getItemCount()).thenReturn(1);
		assertThat(listAdapter.getItemCount()).isEqualTo(1);
	}
}
