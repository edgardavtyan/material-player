package com.edavtyan.materialplayer.components.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {
	@BindView(R.id.back) ImageButton backButton;
	@BindView(R.id.search) EditText searchEditText;
	@BindView(R.id.list) RecyclerView searchResultsList;

	private SearchPresenter presenter;
	private SearchAdapter adapter;

	private View.OnClickListener onBackButtonClickListener = v -> finish();

	private TextWatcher onSearchQueryChangeWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			presenter.onSearchChange(s.toString());
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	@Override
	public int getLayoutId() {
		return R.layout.activity_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		App app = (App) getApplicationContext();
		SearchFactory factory = app.getSearchFactory(this, this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();

		backButton.setOnClickListener(onBackButtonClickListener);
		searchEditText.addTextChangedListener(onSearchQueryChangeWatcher);

		searchResultsList.setAdapter(adapter);
		searchResultsList.setLayoutManager(new LinearLayoutManager(this));

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	}

	public void updateSearchResults(List<Artist> artists) {
		adapter.updateSearchResults(artists);
	}
}
