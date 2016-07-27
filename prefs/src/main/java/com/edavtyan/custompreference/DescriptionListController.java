package com.edavtyan.custompreference;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;


public class DescriptionListController extends ListController<DescriptionListPreference> {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence summary;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<CharSequence> entries;
	private final @Getter List<CharSequence> values;
	private final @Getter List<CharSequence> summaries;


	public DescriptionListController(DescriptionListPreference prefView, AttributeSet attributeSet) {
		super(prefView);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = prefView.context.obtainStyledAttributes(
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
