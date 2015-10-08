package com.edavtyan.materialplayer.app.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.edavtyan.materialplayer.app.R;

public class NowPlayingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        ImageView artView = (ImageView) findViewById(R.id.nowplaying_art);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        artView.getLayoutParams().height = screenSize.x;
    }
}
