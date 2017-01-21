package com.edavtyan.materialplayer.components.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.edavtyan.materialplayer.R;

public class IconsTabsAdapter extends TabsAdapter {
	private final Context context;

	public IconsTabsAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		SpannableStringBuilder stringBuilder = new SpannableStringBuilder(" ");

		if (position == 0) setUpTabIcon(stringBuilder, R.drawable.ic_person);
		if (position == 1) setUpTabIcon(stringBuilder, R.drawable.ic_album);
		if (position == 2) setUpTabIcon(stringBuilder, R.drawable.ic_note);

		return stringBuilder;
	}

	private void setUpTabIcon(SpannableStringBuilder stringBuilder, @DrawableRes int drawableId) {
		Drawable drawable = ContextCompat.getDrawable(context, drawableId);
		drawable.setBounds(0, 0, 36, 36);
		ImageSpan imageSpan = new ImageSpan(drawable);
		stringBuilder.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}
}
