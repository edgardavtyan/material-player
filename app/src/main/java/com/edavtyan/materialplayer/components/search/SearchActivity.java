package com.edavtyan.materialplayer.components.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {
	@BindView(R.id.back) ImageButton backButton;
	@BindView(R.id.search) EditText searchEditText;
	@BindView(R.id.list) RecyclerView searchResultsList;
	@BindView(R.id.tabs) TabLayout tabLayout;
	@BindView(R.id.view_pager) ViewPager viewPager;

	private List<OnSearchQueryChangedListener> onSearchQueryChangedListeners;

	private View.OnClickListener onBackButtonClickListener = v -> finish();

	private TextWatcher onSearchQueryChangeWatcher = new TextWatcher() {
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
