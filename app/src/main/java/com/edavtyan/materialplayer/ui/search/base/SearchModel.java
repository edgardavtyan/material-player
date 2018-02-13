package com.edavtyan.materialplayer.ui.search.base;

public interface SearchModel {
	void setQuery(String query);
	void update();
	int getSearchResultCount();
}
