package com.edavtyan.materialplayer.components.album_detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailAdapter;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailDI;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailMvp;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class AlbumDetailActivityTest extends ActivityTest {
	private static AlbumDetailAdapter adapter;
	private static AlbumDetailMvp.Presenter presenter;
	private static AlbumDetailDI di;

	static {
		adapter = mock(AlbumDetailAdapter.class);
		presenter = mock(AlbumDetailMvp.Presenter.class);
		di = mock(AlbumDetailDI.class);
		doReturn(adapter).when(di).provideAdapter();
		doReturn(presenter).when(di).providePresenter();
	}

	public static class TestAlbumDetailActivity extends AlbumDetailActivity {
		@Override
		protected AlbumDetailDI getDI() {
			return di;
		}
	}

	@Override
	public void beforeEach() {
		super.beforeEach();
		reset(adapter, presenter);
		activity = startActivity(new Intent(context, TestAlbumDetailActivity.class));
	}

	private TestAlbumDetailActivity activity;

	@Test
	public void onCreate_initListView() {
		RecyclerView list = activity.findView(R.id.list);
		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
	}

	@Test
	public void onStart_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		activity.finish();
		verify(presenter).onDestroy();
	}

	@Test
	public void setAlbumTitle_setTitleViewText() {
		TextView titleView = activity.findView(R.id.title);
		runOnUiThread(() -> activity.setAlbumTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setAlbumInfo_setInfoTextWithPattern() {
		TextView infoView = activity.findView(R.id.info);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 9));
		assertThat(infoView.getText()).isEqualTo("artist | 9 Tracks");
	}
}
