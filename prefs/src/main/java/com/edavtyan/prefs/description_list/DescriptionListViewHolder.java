package com.edavtyan.prefs.description_list;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.simple_list.SimpleListViewHolder;

public class DescriptionListViewHolder extends SimpleListViewHolder {
	private final TextView descriptionView;

	public DescriptionListViewHolder(View itemView) {
		super(itemView);
		descriptionView = findView(R.id.description);
	}

	public void setDescription(String description) {
		descriptionView.setText(description);
	}
}
