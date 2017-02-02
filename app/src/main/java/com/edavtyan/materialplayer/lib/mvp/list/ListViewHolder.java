package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class ListViewHolder
		extends BaseViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.menu) ImageButton menuButton;

	private final PopupMenu popupMenu;

	public ListViewHolder(Context context, View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(getMenuResource());
		popupMenu.setOnMenuItemClickListener(this);
	}

	@OnClick(R.id.menu)
	public void onMenuClick() {
		popupMenu.show();
	}

	@MenuRes
	public int getMenuResource() {
		return R.menu.menu_track;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		return false;
	}

	@Override
	public void onClick(View v) {

	}
}
