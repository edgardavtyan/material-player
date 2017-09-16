package com.edavtyan.materialplayer.components.lists.lib;

@SuppressWarnings("unused")
public interface ListMvp {
	interface Model {
		boolean isCompactModeEnabled();
	}

	interface Presenter<VH> {
		void onBindViewHolder(VH holder, int position);
		int getItemCount();
		void onCreate();
		void onDestroy();
		void onUpdateCompactMode();
		boolean isCompactModeEnabled();
	}

	interface View {
		void notifyDataSetChanged();
	}
}
