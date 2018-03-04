package com.edavtyan.materialplayer.testlib.assertions;

import android.os.Bundle;

import org.assertj.core.api.AbstractAssert;

import java.util.HashSet;
import java.util.Set;

public class BundleAssert extends AbstractAssert<BundleAssert, Bundle> {
	public BundleAssert(Bundle actual) {
		super(actual, BundleAssert.class);
	}

	public BundleAssert isEqualTo(Bundle expected) {
		if (!equalBundles(actual, expected)) {
			failWithMessage("Expected bundles to be equal");
		}

		return this;
	}

	public boolean equalBundles(Bundle one, Bundle two) {
		if (one.size() != two.size())
			return false;

		Set<String> setOne = new HashSet<>(one.keySet());
		setOne.addAll(two.keySet());
		Object valueOne;
		Object valueTwo;

		for (String key : setOne) {
			if (!one.containsKey(key) || !two.containsKey(key))
				return false;

			valueOne = one.get(key);
			valueTwo = two.get(key);
			if (valueOne instanceof Bundle && valueTwo instanceof Bundle &&
				!equalBundles((Bundle) valueOne, (Bundle) valueTwo)) {
				return false;
			} else if (valueOne == null) {
				if (valueTwo != null)
					return false;
			} else if (!valueOne.equals(valueTwo))
				return false;
		}

		return true;
	}
}
