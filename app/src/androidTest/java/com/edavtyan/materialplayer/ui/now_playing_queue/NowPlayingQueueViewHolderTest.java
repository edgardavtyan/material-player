package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class NowPlayingQueueViewHolderTest extends BaseTest {
	private NowPlayingQueueViewHolder holder;
	private NowPlayingQueuePresenter presenter;
	private SdkFactory sdkFactory;
	private PopupMenu popupMenu;
	private View itemView;

	@SuppressLint("InflateParams")
	@Override
	public void beforeEach() {
		super.beforeEach();

		popupMenu = mock(PopupMenu.class);

		sdkFactory = mock(SdkFactory.class);
		when(sdkFactory.createPopupMenu(any(), any())).thenReturn(popupMenu);

		presenter = mock(NowPlayingQueuePresenter.class);

		itemView = LayoutInflater.from(context).inflate(R.layout.listitem_track, null, false);
		holder = new NowPlayingQueueViewHolder(context, itemView, presenter, sdkFactory);
	}

	@Override
	public void afterEach() {
		super.afterEach();
	}

	@Test
	public void constructor_initPopupMenu() {
		ImageView menuButton = (ImageView) itemView.findViewById(R.id.menu);

		verify(sdkFactory).createPopupMenu(context, menuButton);
		verify(popupMenu).inflate(R.menu.menu_queue);
		verify(popupMenu).setOnMenuItemClickListener(holder);
	}

	@Test
	public void setTitle_setTitleViewText() {
		TextView titleView = (TextView) itemView.findViewById(R.id.title);
		holder.setTitle("title");
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setInfo_setInfoViewTextWithPattern() {
		TextView infoView = (TextView) itemView.findViewById(R.id.info);
		holder.setInfo(448945, "artist", "album");
		assertThat(infoView.getText()).isEqualTo("07:28 | artist \u2022 album");
	}

	@Test
	public void menuClicked_showPopupMenu() {
		ImageButton menuButton = (ImageButton) itemView.findViewById(R.id.menu);
		menuButton.performClick();
		verify(popupMenu).show();
	}

	@Test
	public void onClick_listenerNotSet_notThrowNPE() {
		assertThatNPENotThrown(() -> holder.onClick(null));
	}

	@Test
	public void onClick_callPresenterWithCorrectPosition() {
		holder.onClick(null);
		verify(presenter).onItemClick(-1);
	}

	@Test
	public void onMenuClickListener_removeItemClicked_callPresenterWithCorrectPosition() {
		clickMenuItemWithId(R.id.menu_remove);
		verify(presenter).onRemoveItemClick(-1);
	}

	@Test
	public void onMenuClickListener_wrongId_doNothing() {
		clickMenuItemWithId(-1);
		verifyZeroInteractions(presenter);
	}

	private void clickMenuItemWithId(@IdRes int menuId) {
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(menuId);
		holder.onMenuItemClick(menuItem);
	}
}
