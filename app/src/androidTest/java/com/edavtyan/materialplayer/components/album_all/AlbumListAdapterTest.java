package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.album_all.AlbumListViewHolder;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListAdapterTest extends BaseTest {
	private AlbumListAdapter adapter;
	private AlbumListMvp.Presenter presenter;
	private AlbumListViewHolder holder;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(AlbumListMvp.Presenter.class);
		holder = mock(AlbumListViewHolder.class);
		adapter = new AlbumListAdapter(context, presenter);
	}

	@Test
	public void onCreateViewHolder_specifyContainerAndNotAttachToRoot() {
		LayoutInflater inflater = spy(LayoutInflater.from(context));
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		AlbumListAdapter adapter = new AlbumListAdapter(context, presenter);
		LinearLayout expectedViewGroup = new LinearLayout(context);
		adapter.onCreateViewHolder(expectedViewGroup, 0);

		verify(inflater).inflate(anyInt(), eq(expectedViewGroup), eq(false));
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		AlbumListViewHolder mockHolder = mock(AlbumListViewHolder.class);

		adapter.onBindViewHolder(mockHolder, 0);
		verify(presenter).bindViewHolder(mockHolder, 0);
	}

	@Test
	public void onClick_callPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onHolderClick(holder);
		verify(presenter).onItemClicked(7);
	}

	@Test
	public void onMenuAddToPlaylistClick_callPresenter() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(7);
		adapter.onMenuAddToPlaylistClick(holder);
		verify(presenter).addToPlaylist(7);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}
}
