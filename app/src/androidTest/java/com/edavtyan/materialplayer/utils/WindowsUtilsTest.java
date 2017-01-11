package com.edavtyan.materialplayer.utils;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.test.filters.SdkSuppress;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.Locale;

import static com.edavtyan.materialplayer.testlib.asertions.ConstructorAssert.assertThatConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SdkSuppress(minSdkVersion = 21)
@TargetApi(21)
public class WindowsUtilsTest extends BaseTest {
	@Test
	public void constructor_private() throws NoSuchMethodException {
		assertThatConstructor(WindowUtils.class.getDeclaredConstructor()).isPrivate();
	}

	@Test
	public void makeStatusBarTransparent_setStatusBarVisibilityAndColor() {
		View decorView = new View(context);
		Window window = mock(Window.class);
		when(window.getDecorView()).thenReturn(decorView);

		WindowUtils.makeStatusBarTransparent(window);

		assertThat(decorView.getSystemUiVisibility()).isEqualTo(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		verify(window).setStatusBarColor(Color.TRANSPARENT);
	}

	@Test
	public void isPortrait_portrait_true() {
		setOrientation(Configuration.ORIENTATION_PORTRAIT);
		assertThat(WindowUtils.isPortrait(context)).isTrue();
	}

	@Test
	public void isPortrait_landscape_false() {
		setOrientation(Configuration.ORIENTATION_LANDSCAPE);
		assertThat(WindowUtils.isPortrait(context)).isFalse();
	}

	private void setOrientation(int orientation) {
		Configuration configuration = new Configuration();
		configuration.orientation = orientation;
		configuration.setLocale(new Locale("en-us")); // Prevents NPE in updateConfiguration method
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		context.getResources().updateConfiguration(configuration, displayMetrics);
	}
}
