package com.edavtyan.prefs.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.base.BasePreference;

public class CheckboxPreference extends BasePreference implements View.OnClickListener {
	private TextView titleView;
	private CheckBox checkboxView;
	private CheckboxPreferencePresenter presenter;

	public CheckboxPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPref(attrs);
	}

	public CheckboxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPref(attrs);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setChecked(boolean checked) {
		checkboxView.setChecked(checked);
	}

	@Override
	public void onClick(View v) {
		presenter.onPrefClicked();
	}

	private void initPref(AttributeSet attrs) {
		setOrientation(HORIZONTAL);
		LayoutInflater.from(context).inflate(R.layout.entry_checkbox, this, true);
		setOnClickListener(this);

		titleView = findView(R.id.title);
		checkboxView = findView(R.id.checkbox);

		CheckboxPreferenceModel model = new CheckboxPreferenceModel(context, attrs);

		if (isInEditMode()) {
			setTitle(model.getTitle());
			setChecked(model.getDefaultValue());
			return;
		}

		presenter = new CheckboxPreferencePresenter(this, model);
		presenter.onInit();
	}
}
