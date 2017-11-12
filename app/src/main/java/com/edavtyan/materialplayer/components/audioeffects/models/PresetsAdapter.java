package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import java.util.ArrayList;
import java.util.List;

public class PresetsAdapter extends BaseAdapter {
	private final List<String> builtInPresets;
	private final List<String> customPresets;
	private final String customNewPreset;
	private final LayoutInflater layoutInflater;

	public PresetsAdapter(Context context) {
		layoutInflater = LayoutInflater.from(context);
		builtInPresets = new ArrayList<>();
		customPresets = new ArrayList<>();
		customNewPreset = context.getString(R.string.equalizer_presets_custom_new);
	}

	public void setBuiltInPresets(List<String> newPresets) {
		builtInPresets.clear();
		builtInPresets.addAll(newPresets);
		notifyDataSetChanged();
	}

	public void setCustomPresets(List<String> newPresets) {
		customPresets.clear();
		customPresets.addAll(newPresets);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return builtInPresets.size() + customPresets.size() + 1;
	}

	@Override
	public String getItem(int position) {
		if (position == 0) {
			return customNewPreset;
		} else if (0 < position && position <= customPresets.size()) {
			return customPresets.get(position - 1);
		} else {
			return builtInPresets.get(position - 1 - customPresets.size());
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) layoutInflater.inflate(R.layout.spinner_simple, parent, false);
		view.setText(getItem(position));
		return view;
	}
}
