package com.edavtyan.materialplayer.testlib.tests;

import android.content.ContentProvider;
import android.content.pm.ProviderInfo;
import android.provider.MediaStore;
import android.test.mock.MockContentResolver;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class DBTest extends BaseTest {
	protected <TProvider extends ContentProvider> void initProvider(TProvider provider) {
		ProviderInfo providerInfo = new ProviderInfo();
		providerInfo.authority = MediaStore.AUTHORITY;
		provider.attachInfo(context, providerInfo);
		MockContentResolver mockContentResolver = spy(new MockContentResolver());
		mockContentResolver.addProvider(MediaStore.AUTHORITY, provider);
		when(context.getContentResolver()).thenReturn(mockContentResolver);
	}
}
