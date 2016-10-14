package com.edavtyan.materialplayer.testlib.tests;

import com.edavtyan.materialplayer.testlib.rules.UiThreadRule;

import org.junit.Rule;

import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTest extends BaseTest {
	@Rule public UiThreadRule uiThreadRule = new UiThreadRule();

	protected  <T> void testFactoryMethod(Callable<T> callable) throws Exception {
		assertThat(callable.call()).isSameAs(callable.call());
	}
}
