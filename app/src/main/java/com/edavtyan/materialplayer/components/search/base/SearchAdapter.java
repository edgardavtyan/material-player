package com.edavtyan.materialplayer.components.search.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.base.BaseViewHolder;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class SearchAdapter<VH extends BaseViewHolder, T>
		extends TestableRecyclerAdapter<VH> {
	private final Context context;
	private final @Getter List<T> data;

	public SearchAdapter(Context context) {
		this.context = context;
		data = new ArrayList<>();
	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View itemView = inflater.inflate(getLayoutId(), parent, false);
		return onCreateViewHolder(itemView);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public void updateData(List<T> newData) {
		data.clear();
		data.addAll(newData);
		notifyDataSetChanged();
	}

	@LayoutRes
	protected abstract int  getLayoutId();

	protected abstract VH onCreateViewHolder(View itemView);
}
