package com.edavtyan.materialplayer.testlib.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.spy;

@SuppressWarnings("unused")
public class BaseTest {
	protected ContextThemeWrapper context;
	protected App app;
	protected Instrumentation instrumentation;

	@Before
	public void beforeEach() {
		instrumentation = InstrumentationRegistry.getInstrumentation();
		Context appContext = instrumentation.getTargetContext();
		context = spy(new ContextThemeWrapper(appContext, R.style.AppTheme_Light_Orange));
		app = (App) instrumentation.getTargetContext().getApplicationContext();
	}

	@After
	public void afterEach() {
		Mockito.validateMockitoUsage();
	}

	protected void runOnUiThread(Runnable runnable) {
		instrumentation.runOnMainSync(runnable);
	}

	@SuppressWarnings("unchecked")
	protected <T> T startActivity(Class<? extends Activity> activityClass) {
		Intent intent = new Intent(context, activityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return (T) InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
	}

	protected byte[] getResourceAsByteArray(String resName) throws IOException {
		InputStream resStream = getClass().getClassLoader().getResourceAsStream(resName);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next = resStream.read();
		while (next > -1) {
			bos.write(next);
			next = resStream.read();
		}
		bos.flush();
		return bos.toByteArray();
	}

	protected File copyResourceToFileSystem(String name, File file) throws IOException {
		byte[] resBytes = getResourceAsByteArray(name);
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(resBytes, 0, resBytes.length);
		outputStream.close();
		return file;
	}
}
