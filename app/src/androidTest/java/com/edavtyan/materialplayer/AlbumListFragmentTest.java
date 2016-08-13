package com.edavtyan.materialplayer;

import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListAdapter;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListFragment;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;
import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class AlbumListFragmentTest extends FragmentTest<AlbumListFragment> {

	private AlbumListMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();
		initFragmentTest(AlbumListFragment.class);
		presenter = mock(AlbumListMvp.Presenter.class);
		fragment.setPresenter(presenter);
	}

	@After
	public void validate() {
		Mockito.validateMockitoUsage();
	}

	@Test
	public void onCreate_setPresenter() {
		AlbumListFragment localFragment = new AlbumListFragment();
		attachFragment(localFragment);
		execTransactionsAndRunTask(() -> assertThat(localFragment.getPresenter()).isNotNull());
	}

	@Test
	public void onCreate_callPresenter() {
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> verify(presenter).onCreate());
	}

	@Test
	public void onCreateView_setRecyclerViewAdapter() {
		attachFragment(fragment);
		execTransactionsAndRunTask(() -> {
			RecyclerView list = (RecyclerView) fragment.getView().findViewById(R.id.list);
			assertThat(list.getAdapter()).isOfAnyClassIn(AlbumListAdapter.class);
			assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
		});
	}

	@Test
	public void onDestroy_callPresenter() {
		attachFragment(fragment);
		removeFragment(fragment);
		execTransactionsAndRunTask(() -> verify(presenter).onDestroy());
	}

	@Test
	public void notifyDataChanged_callAdapter() {
		AlbumListAdapter adapter = spy(new AlbumListAdapter(activity, presenter));
		fragment.setAdapter(adapter);
		fragment.notifyDataChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
