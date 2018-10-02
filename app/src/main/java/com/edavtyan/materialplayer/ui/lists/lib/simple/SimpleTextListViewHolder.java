package com.edavtyan.materialplayer.ui.lists.lib.simple;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SimpleTextListViewHolder extends TestableViewHolder {
	@BindView(R.id.title) TextView textView;
	@BindView(R.id.menu) ImageButton menuButton;

	protected abstract int getMenuRes();

	public SimpleTextListViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(v -> onItemClick());

		PopupMenu menu = new PopupMenu(itemView.getContext(), menuButton);
		menu.inflate(getMenuRes());
		menu.setOnMenuItemClickListener(this::onMenuItemClick);
		menuButton.setOnClickListener(v -> menu.show());
	}

	public void setText(String text) {
		textView.setText(text);
	}

	protected void onItemClick() {

	}

	protected boolean onMenuItemClick(MenuItem menuItem) {
		return false;
	}
}
