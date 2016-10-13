package com.edavtyan.materialplayer.lib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BaseTest {
	protected static ContextThemeWrapper context;
	protected App app;

	@BeforeClass public static void beforeClass() {
		Context appContext = InstrumentationRegistry.getTargetContext();
		context = spy(new ContextThemeWrapper(appContext, R.style.AppTheme_Light_Orange));
	}

	@Before public void beforeEach() {
		app = mock(App.class, CALLS_REAL_METHODS);
		when(context.getApplicationContext()).thenReturn(app);
	}

	@After public void afterEach() {
		Mockito.validateMockitoUsage();
	}

	protected void runOnUiThread(Runnable runnable) {
		InstrumentationRegistry.getInstrumentation().runOnMainSync(runnable);
	}
}
