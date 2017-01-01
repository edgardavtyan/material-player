package com.edavtyan.prefs.description_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.base.ListModel;
import com.edavtyan.prefs.utils.ArrayUtils;

import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class DescriptionListModel extends ListModel {
	private final @Getter String key;
	private final @Getter String title;
	private final @Getter String summary;
	private final @Getter String defaultValue;
	private final @Getter List<String> entries;
	private final @Getter List<String> values;
	private final @Getter List<String> summaries;

	public DescriptionListModel(Context context, AttributeSet attributeSet) {
		super(context);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.DescriptionListPref);
		key = attrs.getString(R.styleable.DescriptionListPref_cp_key);
		title = attrs.getString(R.styleable.DescriptionListPref_cp_title);
		summary = attrs.getString(R.styleable.DescriptionListPref_cp_summary);
		defaultValue = attrs.getString(R.styleable.DescriptionListPref_cp_defaultValue);
		entries = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.DescriptionListPref_cp_entries));
		values = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.DescriptionListPref_cp_entryValues));
		summaries = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.DescriptionListPref_cp_summaries));
	}
}
