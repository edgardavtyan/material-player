package com.edavtyan.materialplayer.components.detail;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseDetailActivityTest extends ActivityTest {
	protected static UtilsFactory utilsFactory;

	protected Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		utilsFactory = mock(UtilsFactory.class, RETURNS_MOCKS);
		when(utilsFactory.provideNavigator(any())).thenReturn(navigator);
	}
}
