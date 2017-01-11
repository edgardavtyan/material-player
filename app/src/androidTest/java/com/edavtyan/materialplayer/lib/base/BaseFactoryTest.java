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
		testFactoryMethod(baseFactory::provideContext);
		testFactoryMethod(baseFactory::provideNavigator);
		testFactoryMethod(baseFactory::provideArtProvider);
		testFactoryMethod(baseFactory::provideMusicTagReader);
		testFactoryMethod(baseFactory::provideArtFileStorage);
		testFactoryMethod(baseFactory::provideArtMemoryCache);
		testFactoryMethod(baseFactory::provideBitmapFactory);
		testFactoryMethod(baseFactory::provideThemeUtils);
		testFactoryMethod(baseFactory::providePrefs);
		testFactoryMethod(baseFactory::provideBasePrefs);
	}
}
