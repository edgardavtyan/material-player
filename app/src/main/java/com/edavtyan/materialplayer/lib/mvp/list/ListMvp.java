package com.edavtyan.materialplayer.lib.mvp.list;

public interface ListMvp {
	interface Model {
		interface OnCompactModeChangedListener {
			void onCompactModeChanged();
		}

		void setOnCompactModeChangedListener(OnCompactModeChangedListener listener);

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
