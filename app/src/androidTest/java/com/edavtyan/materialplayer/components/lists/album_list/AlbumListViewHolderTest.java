package com.edavtyan.materialplayer.components.lists.album_list;

import android.support.annotation.IdRes;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListViewHolderTest extends BaseTest {
	private AlbumListViewHolder holder;
	private View itemView;
	private AlbumListMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_album, null);
		presenter = mock(AlbumListMvp.Presenter.class);
		holder = spy(new AlbumListViewHolder(context, itemView, presenter));
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = holder.findView(R.id.title);
		TextView infoView = holder.findView(R.id.info);

		holder.setTitle("title");
		holder.setInfo(7, "artist");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("artist \u2022 7 Tracks");
	}

	@Test
	public void setArt_nullPath_setFallbackImage() {
		runOnUiThread(() -> holder.setArt(null));
		assertThat(itemView, R.id.art).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void onClick_callPresenterWithCorrectPosition() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(1);
		holder.onClick(null);
		verify(presenter).onHolderClick(1);
	}

	@Test
	public void onMenuItemClick_callPresenterWithCorrectPosition() {
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
