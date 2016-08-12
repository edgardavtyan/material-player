package com.edavtyan.materialplayer;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListAdapter;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListMvp;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListViewHolder;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ArtistListAdapterTest extends BaseTest {

	private ArtistListMvp.Presenter presenter;
	private ArtistListAdapter adapter;
	private ArtistListViewHolder holder;

	@Before
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(ArtistListMvp.Presenter.class);
		adapter = new ArtistListAdapter(context, presenter);
		holder = mock(ArtistListViewHolder.class);
	}

	@Test
	public void onCreateViewHolder_createHolder() {
		ViewGroup parent = new LinearLayout(context);
		ArtistListViewHolder holder = adapter.onCreateViewHolder(parent, 0);
		assertThat(holder).isNotNull();
	}

	@Test
	public void onCreateViewHolder_notAttachToRoot() {
		LayoutInflater inflater = mock(LayoutInflater.class);
		View view = new View(context);
		when(inflater.inflate(anyInt(), any())).thenReturn(view);
		when(inflater.inflate(anyInt(), any(), anyBoolean())).thenReturn(view);

		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

		ArtistListAdapter adapter = new ArtistListAdapter(context, presenter);
		adapter.onCreateViewHolder(null, 0);

		verify(inflater).inflate(anyInt(), any(), eq(false));
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		verify(presenter).bindViewHolder(holder, 0);
	}

	@Test
	public void onBindViewHolder_setClickListener() {
		adapter.onBindViewHolder(holder, 0);
		verify(holder).setOnHolderClickListener(adapter);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}

	@Test
	public void onClick_callPresenter() {
		adapter.onHolderClick(holder, 3);
		verify(presenter).onHolderClicked(holder, 3);
	}
}
