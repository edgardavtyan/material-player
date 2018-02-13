package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListAdapterTest extends BaseTest {
	private ArtistListPresenter presenter;
	private ArtistListViewHolder holder;
	private ArtistListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(ArtistListPresenter.class);
		holder = mock(ArtistListViewHolder.class);
		adapter = new ArtistListAdapter(context, presenter, new SdkFactory());
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		assertThat(adapter.getLayoutId()).isEqualTo(R.layout.listitem_album);
	}

	@Test
	public void onCreateViewHolder_inflateViewWithCorrectParams() {
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
		((ListPresenter<ArtistListViewHolder>)verify(presenter)).onBindViewHolder(holder, 0);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}
}
