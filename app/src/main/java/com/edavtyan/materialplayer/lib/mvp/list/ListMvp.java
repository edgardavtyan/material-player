package com.edavtyan.materialplayer.lib.mvp.list;

public interface ListMvp {
	interface Model {
		interface OnCompactModeChangedListener {
			void onCompactModeChanged();
		}

		void setOnCompactModeChangedListener(OnCompactModeChangedListener listener);

		boolean isCompactModeEnabled();
	}
}
