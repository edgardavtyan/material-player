package com.edavtyan.materialplayer.lib;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.testable.TestableFragment;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class FragmentTest<TFragment extends TestableFragment> extends BaseTest {
	protected App app;
	protected TFragment fragment;
	protected AppCompatActivity activity;
	protected LayoutInflater inflater;
	protected View fragmentView;

	protected void initFragment(TFragment fragment) {
		app = mock(App.class);

		activity = mock(AppCompatActivity.class);
		doReturn(app).when(activity).getApplicationContext();

		this.fragment = spy(fragment);
		doNothing().when(this.fragment).baseOnDestroy();
		doNothing().when(this.fragment).baseOnCreate(null);
		doNothing().when(this.fragment).baseOnStart();
		doNothing().when(this.fragment).baseOnStop();
		doReturn(activity).when(this.fragment).getContext();

		fragmentView = mock(View.class);
		inflater = mock(LayoutInflater.class);
		doReturn(fragmentView).when(inflater).inflate(anyInt(), any(), anyBoolean());
	}
}
