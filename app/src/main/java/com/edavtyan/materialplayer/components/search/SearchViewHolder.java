package com.edavtyan.materialplayer.components.search;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;

public class SearchViewHolder extends BaseViewHolder {
	@BindView(R.id.title) TextView titleView;

	public SearchViewHolder(View itemView) {
		super(itemView);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}
}
