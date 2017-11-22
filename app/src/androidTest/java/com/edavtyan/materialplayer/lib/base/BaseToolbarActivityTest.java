package com.edavtyan.materialplayer.lib.base;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;
import org.mockito.verification.VerificationMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class BaseToolbarActivityTest extends ActivityTest {
	private static TestBaseToolbarActivity activity;

	public static class TestBaseToolbarActivity extends BaseToolbarActivity {
		@Override
		public int getLayoutId() {
			return R.layout.test_toolbar_layout;
		}

		@Override
		protected int getToolbarTitleStringId() {
			return R.string.test_string;
		}
	}

	private Toolbar toolbar;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			activity = spy(startActivity(TestBaseToolbarActivity.class));
		} else {
			reset(activity);
		}

		doNothing().when(activity).baseOnCreate(any());
		runOnUiThread(() -> activity.onCreate(null));

		toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
	}

	@Test
	public void onCreate_setToolbarTitle() {
		assertThat(toolbar.getTitle()).isEqualTo(context.getString(R.string.test_string));
	}

	@Test
	public void onCreate_setSupportActionBar() {
		verify(activity).setSupportActionBar(toolbar);
	}

	@Test
	public void onCreate_backIconEnabled_setDisplayHomeAsUpEnabled() {
		testSetDisplayHomeAsUpEnabled(true, atMost(1));
	}

	@Test
	public void onCreate_backIconDisabled_notSetDisplayHomeAsUpEnabled() {
		testSetDisplayHomeAsUpEnabled(false, never());
	}

	@Test
	public void onOptionsItemSelected_backIconPressed_callOnBackPressed() {
		runOnUiThread(() -> callOnOptionsItemSelectedWithMenuItemId(android.R.id.home));
		verify(activity).finish();
	}

	@Test
	public void onOptionsItemSelected_otherButtonPressed_returnFalse() {
		assertThat(callOnOptionsItemSelectedWithMenuItemId(0)).isFalse();
	}

	private void testSetDisplayHomeAsUpEnabled(boolean enabled, VerificationMode verificationMode) {
		ActionBar actionBar = mock(ActionBar.class);
		doReturn(actionBar).when(activity).getSupportActionBar();
		doReturn(enabled).when(activity).isBackIconEnabled();

		runOnUiThread(() -> activity.onCreate(null));

		verify(actionBar, verificationMode).setDisplayHomeAsUpEnabled(true);
	}

	private boolean callOnOptionsItemSelectedWithMenuItemId(int menuId) {
		MenuItem menuItem = mock(MenuItem.class);
		when(menuItem.getItemId()).thenReturn(menuId);
		return activity.onOptionsItemSelected(menuItem);
	}
}
