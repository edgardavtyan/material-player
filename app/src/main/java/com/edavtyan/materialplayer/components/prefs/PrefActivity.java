package com.edavtyan.materialplayer.components.prefs;


import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

public class PrefActivity extends BaseToolbarActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_pref;
    }

    @Override
    protected int getToolbarTitleStringId() {
        return R.string.pref_title;
    }
}
