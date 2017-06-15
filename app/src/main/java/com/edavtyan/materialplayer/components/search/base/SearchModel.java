package com.edavtyan.materialplayer.components.search.base;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class SearchModel<T> {
	private final @Getter List<T> data;

	public SearchModel() {
		data = new ArrayList<>();
	}

	public T getItemAt(int position) {
		return data.get(position);
	}

	public int getItemCount() {
		return data.size();
	}

	public void update(String query) {
		data.clear();
		data.addAll(getSearchResults(query));
	}

	protected abstract List<T> getSearchResults(String query);
}
