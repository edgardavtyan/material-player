package com.edavtyan.materialplayer.components.artist_mvp;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListViewHolder.OnHolderClickListener;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ArtistListViewHolderTest extends BaseTest {

	private View itemView;
	private ArtistListViewHolder holder;

	@Before
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_artist, null);
		holder = new ArtistListViewHolder(itemView);
	}

	@Test
	public void setters_setAllValues() {
		TextView titleView = (TextView) itemView.findViewById(R.id.title);
		TextView infoView = (TextView) itemView.findViewById(R.id.info);

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

		verify(clickListener).onHolderClick(holder, holder.getAdapterPosition());
	}
}
