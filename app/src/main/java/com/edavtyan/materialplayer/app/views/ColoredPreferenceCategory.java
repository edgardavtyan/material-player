package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.resources.AppColors;

public class ColoredPreferenceCategory extends PreferenceCategory {
    public ColoredPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected View onCreateView(ViewGroup parent) {
        int titleColor = AppColors.getPrimary(getContext());
        if (titleColor == AppColors.getWindowBackground(getContext())) {
            titleColor = AppColors.getTextSecondary(getContext());
        }

        TextView titleView = (TextView) super.onCreateView(parent);
        titleView.setTextColor(titleColor);
        return titleView;
    }
}
