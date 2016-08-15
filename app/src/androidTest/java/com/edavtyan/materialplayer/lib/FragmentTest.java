package com.edavtyan.materialplayer.lib;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.junit.Rule;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public abstract class FragmentTest<TFragment extends Fragment> extends BaseTest {
	@Rule
	public ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

	protected static TestActivity activity;
	protected TFragment fragment;
	private FragmentManager fragmentManager;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = spy(activityRule.getActivity());
		fragmentManager = activity.getSupportFragmentManager();
		reset(activity);
	}

	@SuppressWarnings("EmptyCatchBlock")
	protected void initFragmentTest(Class<TFragment> fragmentClass) {
		try {
			fragment = fragmentClass.newInstance();
		} catch (Exception e) {};
	}

	protected void removeFragment(Fragment fragment) {
		fragmentManager.beginTransaction().remove(fragment).commit();
	}

	protected void attachFragment(Fragment fragment) {
		fragmentManager.beginTransaction().add(fragment, null).commit();
	}

	protected void execTransactionsAndRunTask(Runnable task) {
		activity.runOnUiThread(() -> fragmentManager.executePendingTransactions());
		InstrumentationRegistry.getInstrumentation().waitForIdleSync();
		task.run();
	}
}
