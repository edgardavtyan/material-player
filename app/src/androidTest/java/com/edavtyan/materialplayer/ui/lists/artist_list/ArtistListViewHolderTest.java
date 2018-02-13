package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListViewHolderTest extends BaseTest {
	private View itemView;
	private ArtistListViewHolder holder;
	private ArtistListViewHolder holderSpy;
	private ArtistListPresenter presenter;
	private ContextMenuModule contextMenu;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_album, null);
		presenter = mock(ArtistListPresenter.class);
		contextMenu = mock(ContextMenuModule.class);
		holder = new ArtistListViewHolder(context, itemView, presenter, contextMenu);
		holderSpy = spy(holder);
	}

	@Test
	public void constructor_initContextMenu() {
		verify(contextMenu).init(itemView, R.id.menu, R.menu.menu_track);
		verify(contextMenu).setOnMenuItemClickListener(holder);
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = (TextView) holder.itemView.findViewById(R.id.title);
		TextView infoView = (TextView) holder.itemView.findViewById(R.id.info);

		holder.setInfo(1, 2);
		holder.setTitle("title");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("1 Album \u2022 2 Tracks");
	}

	@Test
	public void onClick_callPresenterWithCorrectPosition() {
		when(holderSpy.getAdapterPositionNonFinal()).thenReturn(1);
		holderSpy.onClick(null);
		verify(presenter).onHolderClick(1, sharedViews);
	}

	@Test
	public void setImage_nullImage_setFallbackResourceId() {
		holder.setImage(null);
		assertThat(itemView, R.id.art).hasImageResource(R.drawable.fallback_artist);
	}

	@Test
	public void setImage_nonNullImage_setGivenImage() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		holder.setImage(art);
		assertThat(itemView, R.id.art).hasImageBitmap(art);
	}
}
