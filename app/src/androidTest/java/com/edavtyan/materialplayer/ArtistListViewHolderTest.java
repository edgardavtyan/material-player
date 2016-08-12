package com.edavtyan.materialplayer;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListViewHolder;
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
	public void setOnClickListener_viewClicked_callOnClick() {
		View.OnClickListener clickListener = mock(View.OnClickListener.class);
		holder.setOnClickListener(clickListener);

		itemView.callOnClick();

		verify(clickListener).onClick(itemView);
	}
}
