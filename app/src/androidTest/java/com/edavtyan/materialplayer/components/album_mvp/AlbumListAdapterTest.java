package com.edavtyan.materialplayer.components.album_mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListAdapter;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListViewHolder;
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

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(AlbumListMvp.Presenter.class);
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
		adapter.onHolderClick(0);
		verify(presenter).onItemClicked(0);
	}

	@Test
	public void onMenuAddToPlaylistClick_callPresenter() {
		adapter.onMenuAddToPlaylistClick(0);
		verify(presenter).addToPlaylist(0);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}
}
