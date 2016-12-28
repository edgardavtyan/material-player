package com.edavtyan.custompreference.simple_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.edavtyan.custompreference.R;

import lombok.Getter;
import lombok.Setter;

public class SimpleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	protected final RadioButton radioButton;
	protected final TextView titleView;

	protected @Getter @Setter String value;
	protected @Setter OnHolderClickListener onHolderClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(String value);
	}

	public SimpleListViewHolder(View itemView) {
		super(itemView);

		radioButton = (RadioButton) itemView.findViewById(R.id.radioButton);
		titleView = (TextView) itemView.findViewById(R.id.title);
		itemView.setOnClickListener(this);
	}

	public void setChecked(boolean checked) {
		radioButton.setChecked(checked);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(value);
		}
	}
}
