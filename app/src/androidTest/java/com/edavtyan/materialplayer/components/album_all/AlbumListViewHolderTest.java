package com.edavtyan.materialplayer.components.album_all;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumListViewHolderTest extends BaseTest {
	private AlbumListViewHolder holder;
	private TextView titleView;
	private TextView infoView;

	@Override public void beforeEach() {
		super.beforeEach();
		View itemView = View.inflate(context, R.layout.listitem_album, null);
		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
		holder = new AlbumListViewHolder(context, itemView);
	}

	@Test public void setters_setAllValues() {
		// TODO: test art setter

		holder.setTitle("title");
		holder.setInfo(7, "artist");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("artist | 7 Tracks");
	}
}
