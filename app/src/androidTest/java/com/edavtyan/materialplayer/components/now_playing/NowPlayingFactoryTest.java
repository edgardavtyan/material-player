package com.edavtyan.materialplayer.components.now_playing;

import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Rule;
import org.junit.Test;

public class NowPlayingFactoryTest extends FactoryTest {
	@Rule
	public final ActivityTestRule<NowPlayingActivity> activityRule
			= new ActivityTestRule<>(NowPlayingActivity.class);

	private NowPlayingFactory nowPlayingFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		nowPlayingFactory = new NowPlayingFactory(activityRule.getActivity());
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(nowPlayingFactory::getModel);
		testFactoryMethod(nowPlayingFactory::getPresenter);
		testFactoryMethod(nowPlayingFactory::getActivity);
		testFactoryMethod(nowPlayingFactory::getArt);
		testFactoryMethod(nowPlayingFactory::getControls);
		testFactoryMethod(nowPlayingFactory::getFab);
		testFactoryMethod(nowPlayingFactory::getInfo);
		testFactoryMethod(nowPlayingFactory::getSeekbar);
	}
}
