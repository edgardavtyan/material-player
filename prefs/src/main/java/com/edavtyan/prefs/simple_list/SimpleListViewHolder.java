package com.edavtyan.prefs.simple_list;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.utils.advanced.GenericViewHolder;

import lombok.Getter;
import lombok.Setter;

public class SimpleListViewHolder extends GenericViewHolder implements View.OnClickListener {
	protected final RadioButton radioButton;
	protected final TextView titleView;

	protected @Getter @Setter String value;
	protected @Setter OnHolderClickListener onHolderClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(String value);
	}

	public SimpleListViewHolder(View itemView) {
		super(itemView);

		radioButton = findView(R.id.radioButton);
		titleView = findView( R.id.title);
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
