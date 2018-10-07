package com.edavtyan.materialplayer.ui.lists.album_list;

import android.support.annotation.IdRes;
import android.support.v4.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumListViewHolderTest extends BaseTest {
	private AlbumListViewHolder holder;
	private AlbumListViewHolder holderSpy;
	private View itemView;
	private AlbumListPresenter presenter;
	private ContextMenuModule contextMenu;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_album, null);
		presenter = mock(AlbumListPresenter.class);
		contextMenu = mock(ContextMenuModule.class);
		holder = new AlbumListViewHolder(context, itemView, presenter, contextMenu);
		holderSpy = spy(holder);
	}

	@Test
	public void constructor_initContextMenu() {
		verify(contextMenu).init(itemView, R.id.menu, R.menu.menu_track);
		verify(contextMenu).setOnMenuItemClickListener(holder);
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = (TextView) itemView.findViewById(R.id.title);
		TextView infoView = (TextView) itemView.findViewById(R.id.info);

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
		ImageView artView = (ImageView) itemView.findViewById(R.id.art);
		when(holderSpy.getAdapterPositionNonFinal()).thenReturn(1);
		holderSpy.onClick(null);
		verify(presenter).onHolderClick(1, new SourceSharedViews(Pair.create(artView, "art")));
	}

	@Test
	public void onMenuItemClick_callPresenterWithCorrectPosition() {
		when(holderSpy.getAdapterPositionNonFinal()).thenReturn(2);
		callMenuItemWithId(R.id.menu_add_to_queue);
		verify(presenter).onAddToQueue(2);
	}

	private void callMenuItemWithId(@IdRes int id) {
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(id);
		holderSpy.onMenuItemClick(menuItem);
	}
}
