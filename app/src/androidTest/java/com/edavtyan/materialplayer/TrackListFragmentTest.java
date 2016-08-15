package com.edavtyan.materialplayer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.components.track_mvp.TrackListAdapter;
import com.edavtyan.materialplayer.components.track_mvp.TrackListFragment;
import com.edavtyan.materialplayer.components.track_mvp.TrackListMvp;
import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class TrackListFragmentTest extends FragmentTest<TrackListFragment> {
	private TrackListMvp.Presenter presenter;
	private TrackListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		initFragmentTest(TrackListFragment.class);

		presenter = mock(TrackListMvp.Presenter.class);
		adapter = mock(TrackListAdapter.class);
		fragment.setPresenter(presenter);
		fragment.setAdapter(adapter);
	}

	@Test
	public void onCreate_setPresenterAndAdapter() {
		TrackListFragment fragment = new TrackListFragment();
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> {
			assertThat(fragment.getPresenter()).isNotNull();
		});
	}

	@Test
	public void onCreate_callPresenter() {
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> {
			verify(presenter).onCreate();
			verifyNoMoreInteractions(presenter);
		});
	}

	@Test
	public void onDestroy_callPresenter() {
		attachFragment(fragment);
		removeFragment(fragment);
		execTransactionsAndRunTask(() -> {
			verify(presenter).onDestroy();
		});
	}

	@Test
	public void onCreateView_initRecyclerView() {
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> {
			RecyclerView list = (RecyclerView) fragment.getView().findViewById(R.id.list);
			assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
			assertThat(list.getAdapter()).isInstanceOf(TrackListAdapter.class);
		});
	}

	@Test
	public void notifyDataChanged_callAdapter() {
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> {
			fragment.notifyDataChanged();
			verify(adapter).notifyDataSetChangedNonFinal();
		});
	}
}
