package com.edavtyan.materialplayer.components.search.base;

public interface SearchModel {
	void setQuery(String query);
	void update();
	int getSearchResultCount();
}
