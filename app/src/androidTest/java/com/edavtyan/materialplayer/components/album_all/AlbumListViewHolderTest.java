package com.edavtyan.materialplayer.components.album_all;

import android.support.annotation.IdRes;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListViewHolder.OnHolderClickListener;
import com.edavtyan.materialplayer.components.album_all.AlbumListViewHolder.OnHolderMenuItemClickListener;
import com.edavtyan.materialplayer.testlib.asertions.ImageViewAssert;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.asertions.ImageViewAssert.assertImageView;
import static com.edavtyan.materialplayer.testlib.asertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AlbumListViewHolderTest extends BaseTest {
	private AlbumListViewHolder holder;
	private View itemView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_album, null);
		holder = new AlbumListViewHolder(context, itemView);
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = holder.findView(R.id.title);
		TextView infoView = holder.findView(R.id.info);

		holder.setTitle("title");
		holder.setInfo(7, "artist");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("artist | 7 Tracks");
	}

	@Test
	public void set_fallback_image_when_given_null_path() {
		runOnUiThread(() -> holder.setArt(null));
		ImageViewAssert.assertThatImageView(itemView, R.id.art).hasDrawableWithId(R.drawable.fallback_cover);
	}

	@Test
	public void not_throw_npe_if_click_listener_not_set() {
		assertThatNPENotThrown(() -> holder.onClick(null));
	}

	@Test
	public void raise_click_listener_when_clicked() {
		OnHolderClickListener listener = mock(OnHolderClickListener.class);
		holder.setOnHolderClickListener(listener);
		holder.onClick(null);
		verify(listener).onHolderClick(holder);
	}

	@Test
	public void not_throw_npe_if_menu_click_listener_not_set() {
		assertThatNPENotThrown(() -> holder.onMenuItemClick(null));
	}

	@Test
	public void raise_menu_click_listener_when_add_to_playlist_menu_item_clicked() {
		OnHolderMenuItemClickListener listener = setupMenuListenerWithId(R.id.menu_addToPlaylist);
		verify(listener).onMenuAddToPlaylistClick(holder);
	}

	@Test
	public void not_raise_menu_click_listener_when_other_menu_item_clicked() {
		OnHolderMenuItemClickListener listener = setupMenuListenerWithId(-1);
		verifyZeroInteractions(listener);
	}

	private OnHolderMenuItemClickListener setupMenuListenerWithId(@IdRes int id) {
		OnHolderMenuItemClickListener listener = mock(OnHolderMenuItemClickListener.class);
		holder.setOnHolderMenuItemClickListener(listener);

		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(id);
		holder.onMenuItemClick(menuItem);

		return listener;
	}
}
