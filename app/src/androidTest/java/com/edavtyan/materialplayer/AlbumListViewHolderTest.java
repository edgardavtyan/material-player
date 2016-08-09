package com.edavtyan.materialplayer;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListViewHolder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlbumListViewHolderTest extends BaseTest {
	private View itemView;
	private AlbumListViewHolder holder;
	private TextView titleView;
	private TextView infoView;
	private ImageView artView;
	private ImageButton menuButton;

	@Override
	public void beforeEach() {
		super.beforeEach();
		itemView = View.inflate(context, R.layout.listitem_album, null);
		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
		artView = (ImageView) itemView.findViewById(R.id.art);
		menuButton = (ImageButton) itemView.findViewById(R.id.menu);
		holder = new AlbumListViewHolder(context, itemView);
	}

	@Test
	public void setters_setAllValues() {
		// TODO: test art setter

		holder.setTitle("title");
		holder.setInfo(7, "artist");

		assertThat(titleView.getText()).isEqualTo("title");
		assertThat(infoView.getText()).isEqualTo("artist | 7 Tracks");
	}

	@Test
	@SuppressLint("SetTextI18n")
	public void getters_returnCorrectValues() {
		Drawable bitmap = ContextCompat.getDrawable(context, android.R.drawable.btn_default);
		titleView.setText("title");
		infoView.setText("info");
		artView.setImageDrawable(bitmap);

		assertThat(holder.getTitle()).isEqualTo("title");
		assertThat(holder.getInfo()).isEqualTo("info");
		assertThat(holder.getArt()).isEqualTo(bitmap);
	}

	@Test
	public void onHolderClickListener_itemViewClick_called() {
		View.OnClickListener listener = mock(View.OnClickListener.class);
		holder.setOnClickListener(listener);
		itemView.callOnClick();
		verify(listener).onClick(any());
	}
}
