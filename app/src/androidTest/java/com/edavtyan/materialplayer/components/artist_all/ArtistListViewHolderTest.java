package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;
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

public class ArtistListViewHolderTest extends BaseTest {
	private View itemView;
	private ArtistListViewHolder holder;
	private ArtistListMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_artist, null);
		presenter = mock(ArtistListMvp.Presenter.class);
		holder = spy(new ArtistListViewHolder(context, itemView, presenter));
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = holder.findView(R.id.title);
		TextView infoView = holder.findView(R.id.info);

		holder.setInfo(1, 2);
		holder.setTitle("title");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("1 Album \u2014 2 Tracks");
	}

	@Test
	public void onClick_callPresenterWithCorrectPosition() {
		when(holder.getAdapterPositionNonFinal()).thenReturn(1);
		holder.onClick(null);
		verify(presenter).onHolderClick(1);
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
