package com.edavtyan.materialplayer.ui.search;

import android.os.Build;
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

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
	@BindView(R.id.back) ImageButton backButton;
	@BindView(R.id.search) EditText searchEditText;
	@BindView(R.id.tabs) TabLayout tabLayout;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.appbar) AppBarLayout appbar;

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	private final List<OnSearchQueryChangedListener> onSearchQueryChangedListeners = new ArrayList<>();

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
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_search);
		ButterKnife.bind(this);

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(themeModule);

		backButton.setOnClickListener(onBackButtonClickListener);
		searchEditText.addTextChangedListener(onSearchQueryChangeWatcher);

		viewPager.setAdapter(new SearchTabsAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(this);
		}
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

	protected SearchDIComponent getComponent() {
		return DaggerSearchDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.searchDIModule(new SearchDIModule(this))
				.build();
	}
}
