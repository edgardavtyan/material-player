package com.edavtyan.materialplayer.lib;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.junit.ClassRule;

public abstract class FragmentTest<TFragment extends Fragment> extends BaseTest {
	@ClassRule
	public static ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

	protected TestActivity activity;
	protected TFragment fragment;
	private FragmentManager fragmentManager;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.getActivity();
		fragmentManager = activity.getSupportFragmentManager();
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
