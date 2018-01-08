package com.edavtyan.materialplayer.lib.base;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class BaseFactoryTest extends FactoryTest {
	private BaseFactory baseFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		baseFactory = new BaseFactory(context);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(baseFactory::getContext);
		testFactoryMethod(baseFactory::getNavigator);
		testFactoryMethod(baseFactory::getThemeUtils);
		testFactoryMethod(baseFactory::getPrefs);
		testFactoryMethod(baseFactory::getBasePrefs);
	}
}
