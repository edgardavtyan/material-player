package com.edavtyan.materialplayer.components.lists.track_list;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListViewHolderTest extends BaseTest {
	private TrackListViewHolder holder;
	private TextView titleView;
	private TextView infoView;
	private TrackListPresenter presenter;

	@SuppressLint("InflateParams")
	@Override
	public void beforeEach() {
		super.beforeEach();
		View itemView = LayoutInflater.from(context).inflate(R.layout.listitem_track, null, false);
		presenter = mock(TrackListPresenter.class);
		holder = spy(new TrackListViewHolder(context, itemView, presenter));
		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
	}

	@Test
	public void setTitle_setTitleViewText() {
		holder.setTitle("title");
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setInfo_setFormattedInfoViewText() {
		holder.setInfo(50000, "artist", "album");
		assertThat(infoView.getText()).isEqualTo("00:50 | artist \u2022 album");
	}

	@Test
	public void onClick_callPresenterWithCorrectPosition() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(1);
		holder.onClick(null);
		verify(presenter).onHolderClick(1);
	}

	@Test
	public void onMenuItemClick_addToPlaylistItemClicked_callPresenterWithCorrectPosition() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(2);
		callMenuItemWithId(R.id.menu_add_to_playlist);
		verify(presenter).onAddToPlaylist(2);
	}

	private void callMenuItemWithId(@IdRes int id) {
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(id);
		holder.onMenuItemClick(menuItem);
	}
}
