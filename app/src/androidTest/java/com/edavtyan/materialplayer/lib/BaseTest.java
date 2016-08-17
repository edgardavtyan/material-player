package com.edavtyan.materialplayer.lib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.R;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;

public class BaseTest {
	protected static ContextThemeWrapper context;

	@BeforeClass
	public static void beforeClass() {
		Context appContext = InstrumentationRegistry.getTargetContext();
		context = spy(new ContextThemeWrapper(appContext, R.style.AppTheme_Light_Orange));
	}

	@Before
	public void beforeEach() {

	}

	@After
	public void afterEach() {
		Mockito.validateMockitoUsage();
	}
}
