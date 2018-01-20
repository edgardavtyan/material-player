package com.edavtyan.materialplayer.screens.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;

public class NowPlayingArtTest extends NowPlayingViewTest {
	private NowPlayingArt art;
	private ImageView artView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		artView = (ImageView) activity.findViewById(R.id.art);
		art = new NowPlayingArt(activity);
	}

	@Test
	public void set_fallback_cover_given_null_art() {
		runOnUiThread(() -> art.setArt(null));
		assertThat(artView).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void set_given_bitmap() {
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> art.setArt(bitmap));
		assertThat(artView).hasImageBitmap(bitmap);
	}
}
