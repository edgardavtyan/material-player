package com.example.custompreference;

import android.content.Context;

public abstract class SummaryController extends BaseController {
	public SummaryController(Context context) {
		super(context);
	}


	public abstract CharSequence getSummary();
}
