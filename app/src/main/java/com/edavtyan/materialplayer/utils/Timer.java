package com.edavtyan.materialplayer.utils;

import android.os.Handler;

public class Timer {
	private final Handler handler;
	private final Runnable innerRunnable;

	public Timer(int interval, Runnable task) {
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
		innerRunnable.run();
	}

	public void stop() {
		handler.removeCallbacks(innerRunnable);
	}
}
