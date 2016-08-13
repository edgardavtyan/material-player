package com.edavtyan.materialplayer.lib;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.junit.BeforeClass;
import org.junit.ClassRule;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public abstract class FragmentTest<TFragment extends Fragment> extends BaseTest {
	@ClassRule
	public static ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

	protected static TestActivity activity;
	protected TFragment fragment;
	private FragmentManager fragmentManager;

	@BeforeClass
	public static void beforeClass() {
		activity = spy(activityRule.getActivity());
	}

	@Override
	public void beforeEach() {
		super.beforeEach();
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
		activity.runOnUiThread(() -> {
			fragmentManager.executePendingTransactions();
			task.run();
		});
	}
}
