package com.edavtyan.materialplayer.components.nowplaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.fragments.ServiceFragment;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.MusicPlayerService;

public class NowPlayingInfoFragment extends ServiceFragment {
	private TextView titleView;
	private TextView infoView;

	/*
	 * BroadcastReceivers
	 */

	private final BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			syncWithService();
		}
	};

	/*
	 * ServiceFragment
	 */

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nowplaying_info, container, false);

		titleView = (TextView) view.findViewById(R.id.title);
		infoView = (TextView) view.findViewById(R.id.info);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().registerReceiver(
				newTrackReceiver,
				new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
		getActivity().bindService(
				new Intent(getActivity(), MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onPause() {
		super.onPause();
		getActivity().unregisterReceiver(newTrackReceiver);
	}

	@Override
	public void onServiceConnected() {
		syncWithService();
	}

	/*
	 * Private methods
	 */

	private void syncWithService() {
		Track track = getService().getQueue().getCurrentTrack();
		String trackMetadata = getResources().getString(
				R.string.nowplaying_info_pattern,
				track.getArtistTitle(),
				track.getAlbumTitle());

		titleView.setText(track.getTitle());
		infoView.setText(trackMetadata);
	}
}
