package com.edavtyan.materialplayer.components.now_playing.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class NowPlayingArtTest extends BaseTest {
	private NowPlayingArt art;
	private ImageView artView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		artView = spy(new ImageView(context));

		NowPlayingActivity activity = mock(NowPlayingActivity.class);
		when(activity.findView(R.id.art)).thenReturn(artView);

		art = new NowPlayingArt(activity);
	}

	@Test
	public void set_fallback_cover_given_null_art() {
		art.setArt(null);
		assertThat(artView).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void set_given_bitmap() {
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		art.setArt(bitmap);
		assertThat(artView).hasImageBitmap(bitmap);
	}
}
