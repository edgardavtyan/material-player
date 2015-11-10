package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.HQEqualizerStats;

public class EqualizerPresetsAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;

    public EqualizerPresetsAdapter(Context context) {
        super(context, R.layout.spinner_equalizer_preset);
        inflater = LayoutInflater.from(context);}

    @Override
    public int getCount() {
        return HQEqualizerStats.PRESETS.length;
    }

    @Override
    public String getItem(int position) {
        return HQEqualizerStats.PRESETS[position].getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_equalizer_preset, parent, false);
        }

        TextView textView = (TextView) view;
        textView.setText(getItem(position));
        return view;
    }
}
