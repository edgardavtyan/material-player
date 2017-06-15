package com.edavtyan.materialplayer.components.search.tracks;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;

public class SearchTrackViewHolder extends BaseViewHolder {
	@BindView(R.id.title) TextView titleView;

	public SearchTrackViewHolder(View itemView) {
		super(itemView);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}
}
