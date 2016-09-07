package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.components.artist_all.ArtistListAdapter;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;
import com.edavtyan.materialplayer.components.artist_all.ArtistListViewHolder;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ArtistListAdapterTest extends BaseTest {

	private ArtistListMvp.Presenter presenter;
	private ArtistListAdapter adapter;
	private ArtistListViewHolder holder;
	private View holderView;
	private LayoutInflater inflater;

	@Before
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(ArtistListMvp.Presenter.class);
		adapter = spy(new ArtistListAdapter(context, presenter));
		holder = mock(ArtistListViewHolder.class);

		inflater = mock(LayoutInflater.class);
		holderView = new View(context);
		when(inflater.inflate(anyInt(), any())).thenReturn(holderView);
		when(inflater.inflate(anyInt(), any(), anyBoolean())).thenReturn(holderView);
		when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);
	}

	@Test
	public void onCreateViewHolder_createHolder() {
		ViewGroup parent = new LinearLayout(context);
		ArtistListViewHolder holder = adapter.onCreateViewHolder(parent, 0);
		assertThat(holder).isNotNull();
	}

	@Test
	public void onCreateViewHolder_notAttachToRoot() {
		adapter.onCreateViewHolder(null, 0);
		verify(inflater).inflate(anyInt(), any(), eq(false));
	}

	@Test
	public void onCreateViewHolder_setClickListener() {
		adapter.onCreateViewHolder(null, 0);
		holderView.performClick();
		verify(adapter).onHolderClick(any(), anyInt());
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		verify(presenter).bindViewHolder(holder, 0);
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
