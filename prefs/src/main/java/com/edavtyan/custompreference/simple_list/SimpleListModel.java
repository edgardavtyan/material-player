package com.edavtyan.custompreference.simple_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.ListModel;
import com.edavtyan.custompreference.utils.ArrayUtils;

import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class SimpleListModel extends ListModel {
	private final @Getter String key;
	private final @Getter String title;
	private final @Getter String summary;
	private final @Getter String defaultValue;
	private final @Getter List<String> entries;
	private final @Getter List<String> values;

	public SimpleListModel(Context context, AttributeSet attributeSet) {
		super(context);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(
				attributeSet, R.styleable.SimpleListPreference);
		key = attrs.getString(R.styleable.SimpleListPreference_cp_key);
		title = attrs.getString(R.styleable.SimpleListPreference_cp_title);
		summary = attrs.getString(R.styleable.SimpleListPreference_cp_summary);
		defaultValue = attrs.getString(R.styleable.SimpleListPreference_cp_defaultValue);
		entries = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entries));
		values = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entryValues));
	}
}
