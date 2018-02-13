package com.edavtyan.materialplayer.ui.audio_effects.presets;

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
	private final Context context;
	private final List<String> presets;
	private final LayoutInflater layoutInflater;

	public PresetsAdapter(Context context) {
		this.context = context;
		presets = new ArrayList<>();
		layoutInflater = LayoutInflater.from(context);
	}

	public void setPresets(List<String> builtInPresets, List<String> customPresets) {
		presets.clear();
		presets.add(context.getString(R.string.equalizer_presets_custom_new));
		presets.addAll(customPresets);
		presets.addAll(builtInPresets);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return presets.size();
	}

	@Override
	public String getItem(int position) {
		return presets.get(position);
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
