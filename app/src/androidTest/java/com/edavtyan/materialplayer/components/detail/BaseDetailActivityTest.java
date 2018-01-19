package com.edavtyan.materialplayer.components.detail;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.UtilsModule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseDetailActivityTest extends ActivityTest {
	protected static ActivityModulesModule activityModulesModule;
	protected static ThemeDaggerModule themeDaggerModule;
	protected static UtilsModule utilsModule;

	protected Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		ActivityToolbarModule toolbarModule = mock(ActivityToolbarModule.class);
		ActivityBaseMenuModule baseMenuModule = mock(ActivityBaseMenuModule.class);
		activityModulesModule = mock(ActivityModulesModule.class, RETURNS_MOCKS);
		when(activityModulesModule.provideActivityToolbarModule(any())).thenReturn(toolbarModule);
		when(activityModulesModule.provideBaseMenuModule(any(), any())).thenReturn(baseMenuModule);

		ScreenThemeModule themeModule = mock(ScreenThemeModule.class);
		themeDaggerModule = mock(ThemeDaggerModule.class, RETURNS_MOCKS);
		when(themeDaggerModule.provideScreenThemeModule(any(), any(), any())).thenReturn(themeModule);

		navigator = mock(Navigator.class);
		utilsModule = mock(UtilsModule.class, RETURNS_MOCKS);
		when(utilsModule.provideNavigator(any())).thenReturn(navigator);
	}
}
