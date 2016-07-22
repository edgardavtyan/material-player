package com.example.custompreference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import lombok.Cleanup;
import lombok.Getter;

public class TestPreference extends SummaryPreference<TestPreference.TestController> {
	public TestPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TestPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void createDialogBuilder(AlertDialog.Builder builder) {
		builder.setMessage(controller.getSummary());
	}

	@Override
	protected void updateDialog() {

	}

	@Override
	protected TestController createController(Context context, AttributeSet attrs) {
		return new TestController(context, attrs);
	}

	class TestController extends SummaryController {
		private final @Getter CharSequence key;
		private final @Getter CharSequence title;
		private final @Getter CharSequence summary;
		private final @Getter CharSequence defaultValue;

		public TestController(Context context, AttributeSet attributeSet) {
			super(context);

			@Cleanup("recycle")
			TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TestPreference);
			key = attrs.getString(R.styleable.TestPreference_cp_key);
			title = attrs.getString(R.styleable.TestPreference_cp_title);
			summary = attrs.getString(R.styleable.TestPreference_cp_summary);
			defaultValue = attrs.getString(R.styleable.TestPreference_cp_defaultValue);
		}
	}
}
