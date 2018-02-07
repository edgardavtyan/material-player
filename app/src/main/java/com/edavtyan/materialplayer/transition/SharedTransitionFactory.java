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
}
