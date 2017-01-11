package com.edavtyan.materialplayer.components.track_all;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListViewHolderTest extends BaseTest {
	private View itemView;
	private TrackListViewHolder holder;
	private TextView titleView;
	private TextView infoView;

	@SuppressLint("InflateParams")
	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = LayoutInflater.from(context).inflate(R.layout.listitem_track, null, false);
		holder = new TrackListViewHolder(context, itemView);
		titleView = holder.findView(R.id.title);
		infoView = holder.findView(R.id.info);
	}

	@Test
	public void setTitle_setTitleViewText() {
		holder.setTitle("title");
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setInfo_setFormattedInfoViewText() {
		holder.setInfo(50000, "artist", "album");
		assertThat(infoView.getText()).isEqualTo("00:50 | artist - album");
	}

	@Test
	public void setOnHolderClickListener_respondToClick() {
		TrackListViewHolder.OnHolderClickListener listener
				= mock(TrackListViewHolder.OnHolderClickListener.class);
		holder.setOnHolderClickListener(listener);
		itemView.performClick();
		verify(listener).onHolderClick(holder);
	}

	@Test
	public void setOnHolderMenuItemClickListener_respondToMenuItemsClicks() {
		TrackListViewHolder.OnHolderMenuItemClickListener listener
				= mock(TrackListViewHolder.OnHolderMenuItemClickListener.class);
		holder.setOnHolderMenuItemClickListener(listener);

		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(R.id.menu_addToPlaylist);
		holder.onMenuItemClick(menuItem);

		verify(listener).onMenuAddToPlaylistClick(holder);
	}
}
