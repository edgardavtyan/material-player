package com.edavtyan.materialplayer.components.search;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ed.libsutils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.utils.ThemeColors;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {
	@BindView(R.id.back) ImageButton backButton;
	@BindView(R.id.search) EditText searchEditText;
	@BindView(R.id.tabs) TabLayout tabLayout;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.appbar) AppBarLayout appbar;

	private List<OnSearchQueryChangedListener> onSearchQueryChangedListeners;

	private final View.OnClickListener onBackButtonClickListener = v -> finish();

	private final TextWatcher onSearchQueryChangeWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			for (OnSearchQueryChangedListener listener : onSearchQueryChangedListeners) {
				listener.onSearchQueryChanged(s.toString());
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	public interface OnSearchQueryChangedListener {
		void onSearchQueryChanged(String query);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		onSearchQueryChangedListeners = new ArrayList<>();

		backButton.setOnClickListener(onBackButtonClickListener);
		searchEditText.addTextChangedListener(onSearchQueryChangeWatcher);

		viewPager.setAdapter(new SearchTabsAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		appbar.setBackgroundColor(colors.getColorPrimary());
		searchEditText.getBackground().setColorFilter(colors.getColorPrimary(), PorterDuff.Mode.SRC_ATOP);
		WindowUtils.setStatusBarColor(this, colors.getColorPrimaryDark());
	}

	public void addOnSearchQueryChangedListener(OnSearchQueryChangedListener listener) {
		onSearchQueryChangedListeners.add(listener);
	}

	public void removeOnSearchQueryChangedListener(OnSearchQueryChangedListener listener) {
		onSearchQueryChangedListeners.remove(listener);
	}

	public String getSearchQuery() {
		return searchEditText.getText().toString();
	}
}
