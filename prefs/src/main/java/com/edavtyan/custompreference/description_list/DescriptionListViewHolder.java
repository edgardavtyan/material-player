package com.edavtyan.custompreference.description_list;

import android.view.View;
import android.widget.TextView;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.simple_list.SimpleListViewHolder;

public class DescriptionListViewHolder extends SimpleListViewHolder {
	private final TextView descriptionView;

	public DescriptionListViewHolder(View itemView) {
		super(itemView);
		descriptionView = (TextView) itemView.findViewById(R.id.description);
	}

	public void setDescription(String description) {
		descriptionView.setText(description);
	}
}
