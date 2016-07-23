package com.example.custompreference;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import lombok.Setter;

public class DescriptionListViewHolder extends RecyclerView.ViewHolder
		implements View.OnClickListener {
	private final RadioButton radioButton;
	private final TextView titleView;
	private final TextView descriptionView;
	private @Setter CharSequence value;
	private @Setter OnHolderClickListener onHolderClickListener;


	public interface OnHolderClickListener {
		void onHolderClick(CharSequence value);
	}


	public DescriptionListViewHolder(View itemView) {
		super(itemView);

		itemView.setOnClickListener(this);
		radioButton = (RadioButton) itemView.findViewById(R.id.radioButton);
		titleView = (TextView) itemView.findViewById(R.id.title);
		descriptionView = (TextView) itemView.findViewById(R.id.description);
	}


	public void setChecked(boolean checked) {
		radioButton.setChecked(checked);
	}

	public void setTitle(CharSequence title) {
		titleView.setText(title);
	}

	public void setDescription(CharSequence description) {
		descriptionView.setText(description);
	}


	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(value);
		}
	}
}
