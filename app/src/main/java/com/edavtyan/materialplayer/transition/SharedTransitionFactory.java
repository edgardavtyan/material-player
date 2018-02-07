package com.edavtyan.materialplayer.transition;

public class SharedTransitionFactory {

	public static SharedTransition getExitTransition(TransitionType transitionType) {
		switch (transitionType) {
		case FADE_OUT:
			return new ExitFadeOutTransition();
		case TRANSLATE:
			return new ExitTranslateTransition();
		}

		return null;
	}

	public static SharedTransition getEnterTransition(TransitionType transitionType) {
		switch (transitionType) {
		case FADE_OUT:
			return new EnterFadeOutTransition();
		case TRANSLATE:
			return new EnterTranslateTransition();
		}

		return null;
	}
}
