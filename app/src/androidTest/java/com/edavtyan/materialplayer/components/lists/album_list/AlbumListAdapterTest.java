package com.edavtyan.materialplayer.components.lists.album_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListAdapterTest extends BaseTest {
	private AlbumListPresenter presenter;
	private AlbumListViewHolder holder;
	private AlbumListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(AlbumListPresenter.class);
		holder = mock(AlbumListViewHolder.class);
		adapter = new AlbumListAdapter(context, presenter);
	}

	@Test
	public void getNormalLayoutId_returnCorrectLayout() {
		assertThat(adapter.getNormalLayoutId()).isEqualTo(R.layout.listitem_album);
	}

	@Test
	public void getCompactLayoutId_returnCorrectLayout() {
		assertThat(adapter.getCompactLayoutId()).isEqualTo(R.layout.listitem_album_compact);
	}

	@Test
	public void onCreateViewHolder_inflateWithCorrectViewAndParams() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		LinearLayout parent = new LinearLayout(context);
		adapter.onCreateViewHolder(parent, R.layout.listitem_album);

		verify(inflater).inflate(R.layout.listitem_album, parent, false);
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		//noinspection RedundantCast
		((ListPresenter<AlbumListViewHolder>)verify(presenter)).onBindViewHolder(holder, 0);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}
}
