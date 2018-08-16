package com.edavtyan.materialplayer.ui.now_playing.models;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingLyrics {
	@BindView(R.id.lyrics_wrapper) FrameLayout lyricsWrapper;
	@BindView(R.id.lyricsbox) TextView lyricsView;
	@BindView(R.id.lyricsScroller) ScrollView lyricsScrollerView;
	@BindView(R.id.error_lyricsConnection) TextView lyricsConnectionErrorView;
	@BindView(R.id.error_lyricsNotFound) TextView lyricsNotFoundErrorView;

	private boolean isLyricsEnabled = true;

	@SuppressLint("ClickableViewAccessibility")
	public NowPlayingLyrics(NowPlayingActivity activity) {
		ButterKnife.bind(this, activity);
		lyricsScrollerView.setOnTouchListener((v, e) -> !isLyricsEnabled);
	}

	public void setLyrics(String lyrics) {
		lyricsView.setText(lyrics);
	}

	public void hide() {
		lyricsWrapper.animate().alpha(0f);
		isLyricsEnabled = false;
	}

	public void show() {
		lyricsWrapper.animate().alpha(1f);
		isLyricsEnabled = true;
	}

	public void displayNotFoundError() {
		lyricsScrollerView.setVisibility(View.INVISIBLE);
		lyricsConnectionErrorView.setVisibility(View.INVISIBLE);
		lyricsNotFoundErrorView.setVisibility(View.VISIBLE);
	}

	public void displayConnectionError() {
		lyricsScrollerView.setVisibility(View.INVISIBLE);
		lyricsNotFoundErrorView.setVisibility(View.INVISIBLE);
		lyricsConnectionErrorView.setVisibility(View.VISIBLE);
	}

	public View getWrapper() {
		return lyricsWrapper;
	}
}
