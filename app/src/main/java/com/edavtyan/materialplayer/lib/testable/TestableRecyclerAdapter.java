package com.edavtyan.materialplayer.lib.testable;

import android.support.v7.widget.RecyclerView;

// A wrapper over RecyclerView.Adapter which provides non-final method replacements for final super
// methods (such as notifyDataSetChanged) to allow mocking them using the Mockito framework
public abstract class TestableRecyclerAdapter<VH extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<VH> {

	public void notifyDataSetChangedNonFinal() {
		notifyDataSetChanged();
	}
}
