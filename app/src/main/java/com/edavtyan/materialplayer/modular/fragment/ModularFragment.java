package com.edavtyan.materialplayer.modular.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

import java.util.ArrayList;

import butterknife.ButterKnife;

public abstract class ModularFragment extends Fragment implements ThemeableScreen {

	private final ArrayList<UniversalViewModule> modules = new ArrayList<>();

	@LayoutRes
	protected abstract int getLayoutId();

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(getLayoutId(), container, false);

		ButterKnife.bind(this, view);

		for (UniversalViewModule module : modules) {
			if (module instanceof FragmentModule) {
				((FragmentModule) module).onCreateView(view);
			}
		}

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		for (UniversalViewModule module : modules) {
			if (module instanceof FragmentModule) {
				((FragmentModule) module).onResume();
			}
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		for (UniversalViewModule module : modules) {
			module.onStart();
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		for (UniversalViewModule module : modules) {
			module.onStop();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		for (UniversalViewModule module : modules) {
			if (module instanceof FragmentModule) {
				((FragmentModule) module).onDestroy();
			}
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		for (UniversalViewModule module : modules) {
			module.onCreateOptionsMenu(menu);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		for (UniversalViewModule module : modules) {
			module.onOptionsItemSelected(item);
		}

		return false;
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		for (UniversalViewModule module : modules) {
			module.onThemeChanged(colors);
		}
	}

	protected void addModule(UniversalViewModule module) {
		modules.add(module);
	}
}
