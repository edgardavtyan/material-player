package com.edavtyan.materialplayer.ui.detail;

import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.UtilsDIModule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseDetailActivityTest extends ActivityTest {
	protected static UtilsDIModule utilsFactory;

	protected Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		utilsFactory = mock(UtilsDIModule.class, RETURNS_MOCKS);
		when(utilsFactory.provideNavigator(any())).thenReturn(navigator);
	}
}
