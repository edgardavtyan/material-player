package com.edavtyan.materialplayer;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.components.track_mvp.TrackListViewHolder;
import com.edavtyan.materialplayer.lib.BaseTest;

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

	@Override
	@SuppressLint("InflateParams")
	public void beforeEach() {
		super.beforeEach();
		itemView = LayoutInflater.from(context).inflate(R.layout.listitem_track, null, false);
		holder = new TrackListViewHolder(context, itemView);
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

		verify(listener).onAddToPlaylistMenuItemClick(holder);
	}
}
