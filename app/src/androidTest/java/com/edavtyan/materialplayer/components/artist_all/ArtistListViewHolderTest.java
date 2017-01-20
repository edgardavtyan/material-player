package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.artist_all.ArtistListViewHolder.OnHolderClickListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.asertions.Assertions.assertThat;
import static com.edavtyan.materialplayer.testlib.asertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArtistListViewHolderTest extends BaseTest {
	private View itemView;
	private ArtistListViewHolder holder;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_artist, null);
		holder = new ArtistListViewHolder(context, itemView);
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = holder.findView(R.id.title);
		TextView infoView = holder.findView(R.id.info);

		holder.setInfo(1, 2);
		holder.setTitle("title");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("1 Album | 2 Tracks");
	}

	@Test
	public void setOnHolderClickListener_viewClicked_callOnClick() {
		OnHolderClickListener clickListener = mock(OnHolderClickListener.class);
		holder.setOnHolderClickListener(clickListener);

		itemView.callOnClick();

		verify(clickListener).onHolderClick(holder);
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

	@Test
	public void setOnHolderClickListener_listenerNotSet_notThrowException() {
		assertThatNPENotThrown(() -> holder.onClick(mock(View.class)));
	}
}
