package com.edavtyan.materialplayer.components.nowplaying2;

import android.os.Handler;

public class Timer {
	private final int interval;
	private final Handler handler;
	private final Runnable innerRunnable;

	public Timer(int interval, Runnable task) {
		this.interval = interval;
		this.handler = new Handler();
		this.innerRunnable = new Runnable() {
			@Override
			public void run() {
				task.run();
				handler.postDelayed(this, interval);
			}
		};
	}

	public void run() {
		handler.postDelayed(innerRunnable, interval);
	}

	public void stop() {
		handler.removeCallbacks(innerRunnable);
	}
}
