package com.edavtyan.materialplayer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v7.view.ContextThemeWrapper;

import org.junit.Before;

public class BaseTest {
	protected ContextThemeWrapper context;

	@Before
	public void beforeEach() {
		Context appContext = InstrumentationRegistry.getTargetContext();
		context = new ContextThemeWrapper(appContext, R.style.AppTheme_Light_Orange);
	}
}
