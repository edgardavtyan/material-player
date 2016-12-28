package com.edavtyan.custompreference.description_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.ListModel;

import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class DescriptionListModel extends ListModel {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence summary;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<CharSequence> entries;
	private final @Getter List<CharSequence> values;
	private final @Getter List<CharSequence> summaries;

	public DescriptionListModel(Context context, AttributeSet attributeSet) {
		super(context);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(
				attributeSet, R.styleable.DescriptionList);
		key = attrs.getString(R.styleable.DescriptionList_cp_key);
		title = attrs.getString(R.styleable.DescriptionList_cp_title);
		summary = attrs.getString(R.styleable.DescriptionList_cp_summary);
		defaultValue = attrs.getString(R.styleable.DescriptionList_cp_defaultValue);
		entries = Arrays.asList(attrs.getTextArray(R.styleable.DescriptionList_cp_entries));
		values = Arrays.asList(attrs.getTextArray(R.styleable.DescriptionList_cp_entryValues));
		summaries = Arrays.asList(attrs.getTextArray(R.styleable.DescriptionList_cp_summaries));
	}
}
