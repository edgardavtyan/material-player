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
	private final LayoutInflater layoutInflater;

	public PresetsAdapter(Context context) {
		layoutInflater = LayoutInflater.from(context);
		builtInPresets = new ArrayList<>();
	}

	public void setBuiltInPresets(List<String> newPresets) {
		builtInPresets.clear();
		builtInPresets.addAll(newPresets);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return builtInPresets.size();
	}

	@Override
	public String getItem(int position) {
		return builtInPresets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) layoutInflater.inflate(R.layout.spinner_simple, parent, false);
		view.setText(builtInPresets.get(position));
		return view;
	}
}
