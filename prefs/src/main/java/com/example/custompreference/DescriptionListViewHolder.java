package com.example.custompreference;

import android.view.View;
import android.widget.TextView;

public class DescriptionListViewHolder extends SimpleListViewHolder {

	private final TextView descriptionView;

	
	public DescriptionListViewHolder(View itemView) {
		super(itemView);
		descriptionView = (TextView) itemView.findViewById(R.id.description);
	}


	public void setDescription(CharSequence description) {
		descriptionView.setText(description);
	}
}
