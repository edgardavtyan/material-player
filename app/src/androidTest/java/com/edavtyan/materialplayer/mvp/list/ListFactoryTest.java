package com.edavtyan.materialplayer.list;

import com.edavtyan.materialplayer.components.lists.lib.ListFactory;
import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class ListFactoryTest extends FactoryTest {
	private ListFactory factory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		factory = new ListFactory(context);
	}

	@Test
	public void test_factory_methods() throws Exception {
		testFactoryMethod(factory::getCompactListPref);
	}
}
