package com.edavtyan.materialplayer.components.artist_all;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.artist_all.ArtistListViewHolder.OnHolderClickListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArtistListViewHolderTest extends BaseTest {
	private View itemView;
	private ArtistListViewHolder holder;

	@Override public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_artist, null);
		holder = new ArtistListViewHolder(context, itemView);
	}

	@Test public void setters_setAllValues() {
		TextView titleView = (TextView) itemView.findViewById(R.id.title);
		TextView infoView = (TextView) itemView.findViewById(R.id.info);

		holder.setInfo(1, 2);
		holder.setTitle("title");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("1 Album | 2 Tracks");
	}

	@Test public void setOnHolderClickListener_viewClicked_callOnClick() {
		OnHolderClickListener clickListener = mock(OnHolderClickListener.class);
		holder.setOnHolderClickListener(clickListener);

		itemView.callOnClick();

		verify(clickListener).onHolderClick(holder);
	}
}