package com.edavtyan.materialplayer.ui.lists.album_list;

import java.util.ArrayDeque;

public class AlbumListImageTaskQueue {
	private final ArrayDeque<AlbumListImageTask> tasks;

	public AlbumListImageTaskQueue() {
		tasks = new ArrayDeque<>();
	}

	public void addTask(AlbumListImageTask task) {
		tasks.add(task);
	}

	public void run() {
		tasks.poll().execute();
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public void onPostExecute() {
		if (!tasks.isEmpty()) {
			tasks.poll().execute();
		}
	}
}
