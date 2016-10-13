package com.edavtyan.materialplayer.components.now_playing_queue;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.now_playing_queue.PlaylistViewHolder.OnHolderClickListener;
import com.edavtyan.materialplayer.components.now_playing_queue.PlaylistViewHolder.OnMenuClickListener;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistViewHolderTest extends BaseTest {
	private PlaylistViewHolder holder;
	private View itemView;
	private SdkFactory sdkFactory;
	private PopupMenu popupMenu;

	@SuppressLint("InflateParams")
	@Override public void beforeEach() {
		super.beforeEach();

		popupMenu = mock(PopupMenu.class);

		sdkFactory = mock(SdkFactory.class);
		when(sdkFactory.createPopupMenu(any(), any())).thenReturn(popupMenu);
		when(app.getSdkFactory()).thenReturn(sdkFactory);

		itemView = LayoutInflater.from(context).inflate(R.layout.listitem_track, null, false);
		holder = new PlaylistViewHolder(context, itemView);
	}

	@Test public void constructor_initPopupMenu() {
		ImageView menuButton = holder.findView(R.id.menu);

		verify(sdkFactory).createPopupMenu(context, menuButton);
		verify(popupMenu).inflate(R.menu.menu_queue);
		verify(popupMenu).setOnMenuItemClickListener(holder);
	}

	@Test public void setTitle_setTitleViewText() {
		TextView titleView = holder.findView(R.id.title);
		holder.setTitle("title");
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test public void setInfo_setInfoViewTextWithPattern() {
		TextView infoView = holder.findView(R.id.info);
		holder.setInfo(448945, "artist", "album");
		assertThat(infoView.getText()).isEqualTo("07:28 | artist - album");
	}

	@Test public void menuClicked_showPopupMenu() {
		ImageButton menuButton = holder.findView(R.id.menu);
		menuButton.performClick();
		verify(popupMenu).show();
	}

	@Test public void onHolderClickListener_itemViewClicked_called() {
		OnHolderClickListener listener = mock(OnHolderClickListener.class);
		holder.setOnHolderClickListener(listener);
		itemView.performClick();
		verify(listener).onHolderClick(holder);
	}

	@Test public void onMenuClickListener_removeItemClicked_onRemoveFromQueueClickCalled() {
		OnMenuClickListener listener = mock(OnMenuClickListener.class);
		holder.setOnMenuClickListener(listener);
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(R.id.menu_remove);

		holder.onMenuItemClick(menuItem);

		verify(listener).onRemoveFromQueueClick(holder);
	}
}
