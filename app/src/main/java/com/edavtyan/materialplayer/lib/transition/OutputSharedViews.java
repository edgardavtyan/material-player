package com.edavtyan.materialplayer.lib.transition;

import android.view.View;

import lombok.Getter;

public class OutputSharedViews {
	private final @Getter SharedViewSet[] sharedViewSets;
	private final @Getter View[] enterFadingViews;
	private final @Getter View[] exitPortraitFadingViews;
	private final @Getter View[] exitLandscapeFadingViews;

	private OutputSharedViews(
			SharedViewSet[] sharedViewSets,
			View[] enterFadingViews,
			View[] exitPortraitFadingViews,
			View[] exitLandscapeFadingViews) {
		this.sharedViewSets = sharedViewSets;
		this.enterFadingViews = enterFadingViews;
		this.exitPortraitFadingViews = exitPortraitFadingViews;
		this.exitLandscapeFadingViews = exitLandscapeFadingViews;
	}

	public static Builder builder() {
		return new Builder();
	}

	@SuppressWarnings("UnusedReturnValue")
	public static class Builder {
		private SharedViewSet[] sharedViewSets;
		private View[] enterFadingViews;
		private View[] exitPortraitFadingViews;
		private View[] exitLandscapeFadingViews;

		public Builder() {
			sharedViewSets = new SharedViewSet[0];
			enterFadingViews = new View[0];
			exitPortraitFadingViews = new View[0];
			exitLandscapeFadingViews = new View[0];
		}

		public OutputSharedViews build() {
			return new OutputSharedViews(
					sharedViewSets,
					enterFadingViews,
					exitPortraitFadingViews,
					exitLandscapeFadingViews);
		}

		public Builder sharedViewSets(SharedViewSet... views) {
			sharedViewSets = views;
			return this;
		}

		public Builder enterFadingViews(View... views) {
			enterFadingViews = views;
			return this;
		}

		public Builder exitPortraitFadingViews(View... views) {
			exitPortraitFadingViews = views;
			return this;
		}

		public Builder exitLandscapeFadingViews(View... views) {
			exitLandscapeFadingViews = views;
			return this;
		}
	}
}
