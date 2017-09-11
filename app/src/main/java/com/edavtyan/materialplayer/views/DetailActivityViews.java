package com.edavtyan.materialplayer.views;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivityViews {
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	public DetailActivityViews(AppCompatActivity activity) {
		ButterKnife.bind(this, activity);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String info) {
		infoView.setText(info);
	}

	public void setArt(Bitmap art, @DrawableRes int fallback) {
		if (art == null) {
			artView.setImageResource(fallback);
		} else {
			artView.setImageBitmap(art);
		}
	}
}
