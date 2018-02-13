package com.edavtyan.materialplayer.mvp.list;

import com.edavtyan.materialplayer.ui.lists.lib.ListModel;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListPresenterTest extends BaseTest {

	private ListPresenter listPresenter;
	private ListModel model;
	private ListView view;

	@Override
	public void beforeEach() {
		super.beforeEach();
		model = mock(ListModel.class);
		view = mock(ListView.class);
		listPresenter = new TestListPresenter(model, view);
	}

	@Test
	public void set_compact_mode_on_create() {
		when(model.isCompactModeEnabled()).thenReturn(true);
		listPresenter.onCreate();
	}

	@Test
	public void notify_view_when_compact_mode_changes() {
		// was false before, so changing it to true
		when(model.isCompactModeEnabled()).thenReturn(true);
		listPresenter.onUpdateCompactMode();
		verify(view).notifyDataSetChanged();
	}

	@Test
	public void do_not_notify_data_changed_when_compact_mode_does_not_change() {
		listPresenter.onUpdateCompactMode();
		verify(view, never()).notifyDataSetChanged();
	}

	@Test
	public void return_compact_mode_status_from_model() {
		when(model.isCompactModeEnabled()).thenReturn(true);
		assertThat(listPresenter.isCompactModeEnabled()).isTrue();
	}
}
