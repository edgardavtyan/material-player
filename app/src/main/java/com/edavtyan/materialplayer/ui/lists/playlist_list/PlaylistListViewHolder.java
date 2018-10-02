package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistListViewHolder extends TestableViewHolder {
	@BindView(R.id.title) TextView titleView;

	public PlaylistListViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void setText(String text) {
		titleView.setText(text);
	}
}
