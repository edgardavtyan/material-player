package com.edavtyan.prefs.description_list;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.prefs.R2;
import com.edavtyan.prefs.simple_list.SimpleListViewHolder;

import butterknife.BindView;

public class DescriptionListViewHolder extends SimpleListViewHolder {
	@BindView(R2.id.description) TextView descriptionView;

	public DescriptionListViewHolder(View itemView) {
		super(itemView);
	}

	public void setDescription(String description) {
		descriptionView.setText(description);
	}
}
