package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.materialplayer.R;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class SummaryListAttributes {
	private @Getter List<CharSequence> entries;
	private @Getter List<CharSequence> summaries;
	private @Getter List<CharSequence> values;

	//---

	public SummaryListAttributes(Context context, AttributeSet attributeSet) {
		TypedArray attrs = null;
		try {
			attrs = context.obtainStyledAttributes(attributeSet, R.styleable.SummaryListPref);
			entries = Arrays.asList(attrs.getTextArray(R.styleable.SummaryListPref_entries));
			summaries = Arrays.asList(attrs.getTextArray(R.styleable.SummaryListPref_summaries));
			values = Arrays.asList(attrs.getTextArray(R.styleable.SummaryListPref_values));
		} finally {
			if (attrs != null) attrs.recycle();
		}
	}
}
