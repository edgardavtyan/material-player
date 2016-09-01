package com.edavtyan.materialplayer.lib;

import android.provider.MediaStore;
import android.test.mock.MockContentResolver;

import com.edavtyan.materialplayer.lib.db.TestProvider;

import static org.mockito.Mockito.when;

public class DBTest extends BaseTest {
	protected static <TProvider extends TestProvider>
	void initProvider(Class<TProvider> providerClass) {
		TProvider provider = null;

		try {
			provider = providerClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		provider.attachInfo(context, null);

		MockContentResolver mockContentResolver = new MockContentResolver();
		mockContentResolver.addProvider(MediaStore.AUTHORITY, provider);

		when(context.getContentResolver()).thenReturn(mockContentResolver);
	}
}
