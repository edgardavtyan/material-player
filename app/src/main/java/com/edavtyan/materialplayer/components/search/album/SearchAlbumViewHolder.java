package com.edavtyan.materialplayer.components.search.album;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;

public class SearchAlbumViewHolder extends BaseViewHolder {
	@BindView(R.id.title) TextView titleView;

	public SearchAlbumViewHolder(View itemView) {
		super(itemView);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}
}
