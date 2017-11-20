package com.edavtyan.materialplayer.modular.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.testable.TestableFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

public abstract class ModularFragment extends TestableFragment {
	protected App app;
	private ArrayList<FragmentModule> modules = new ArrayList<>();

	@LayoutRes
	protected abstract int getLayoutId();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (App) getActivity().getApplication();
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(getLayoutId(), container, false);

		ButterKnife.bind(this, view);

		for (FragmentModule module : modules) {
			module.onCreateView(view);
		}

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		for (FragmentModule module : modules) {
			module.onCreate();
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		for (FragmentModule module : modules) {
			module.onStart();
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		for (FragmentModule module : modules) {
			module.onStop();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		for (FragmentModule module : modules) {
			module.onDestroy();
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		for (FragmentModule module : modules) {
			module.onCreateOptionsMenu();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		for (FragmentModule module : modules) {
			module.onOptionsItemSelected();
		}

		return false;
	}

	protected void addModule(FragmentModule module) {
		modules.add(module);
	}
}
