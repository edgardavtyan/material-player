package com.edavtyan.materialplayer.lib;

import android.support.annotation.Nullable;

import lombok.Getter;

@Getter
public class AsyncTaskResult<T> {
	private @Nullable T result;
	private @Nullable Exception exception;

	public AsyncTaskResult(@Nullable T result, @Nullable Exception exception) {
		this.result = result;
		this.exception = exception;
	}
}
