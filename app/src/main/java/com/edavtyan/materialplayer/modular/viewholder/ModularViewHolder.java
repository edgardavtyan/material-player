package com.edavtyan.materialplayer.modular.viewholder;

import android.view.View;

import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

import java.util.List;

public class ModularViewHolder extends TestableViewHolder {
	private final View itemView;

	private List<ViewHolderModule> modules;

	public ModularViewHolder(View itemView) {
		super(itemView);
		this.itemView = itemView;
	}

	public void addModule(ViewHolderModule module) {
		modules.add(module);
		module.init(itemView);
	}
}
