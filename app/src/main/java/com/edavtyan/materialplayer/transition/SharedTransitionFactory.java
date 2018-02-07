package com.edavtyan.materialplayer.transition;

import java.util.HashMap;

public class SharedTransitionFactory {

	private interface Factory {
		SharedTransition create();
	}

	private static HashMap<TransitionType, Factory> enterMap;
	private static HashMap<TransitionType, Factory> exitMap;

	static {
		exitMap = new HashMap<>();
		exitMap.put(TransitionType.TRANSLATE, ExitTranslateTransition::new);
		exitMap.put(TransitionType.FADE_OUT, ExitFadeOutTransition::new);

		enterMap = new HashMap<>();
		enterMap.put(TransitionType.TRANSLATE, EnterTranslateTransition::new);
		enterMap.put(TransitionType.FADE_OUT, EnterFadeOutTransition::new);
	}

	public static SharedTransition getExitTransition(TransitionType transitionType) {
		return exitMap.get(transitionType).create();
	}

	public static SharedTransition getEnterTransition(TransitionType transitionType) {
		return enterMap.get(transitionType).create();
	}
}
