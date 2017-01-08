package com.edavtyan.materialplayer.lib.lastfm;

import android.annotation.SuppressLint;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastfmArtistInfoTests extends BaseTest {
	private LastfmArtistInfo first;
	private LastfmArtistInfo second;

	@Override
	public void beforeEach() {
		super.beforeEach();
		first = createArtistInfo();
		second = createArtistInfo();
	}

	@Test
	public void toJson_returnCorrectJson() throws JSONException {
		LastfmArtistInfo info = new LastfmArtistInfo();
		info.setLargeImageUrl("large");
		info.setExtraLargeImageUrl("xlarge");
		info.setMegaImageUrl("mega");
		String jsonString = info.toJson();
		JSONObject json = new JSONObject(jsonString);
		assertThat(json.getString("large")).isEqualTo("large");
		assertThat(json.getString("xlarge")).isEqualTo("xlarge");
		assertThat(json.getString("mega")).isEqualTo("mega");
	}

	@Test
	public void fromJson_formCorrectInfo() {
		String json = "{\"large\":\"0\",\"xlarge\":\"1\",\"mega\":\"2\"}";
		LastfmArtistInfo info = LastfmArtistInfo.fromJson(json);
		assertThat(info.getLargeImageUrl()).isEqualTo("0");
		assertThat(info.getExtraLargeImageUrl()).isEqualTo("1");
		assertThat(info.getMegaImageUrl()).isEqualTo("2");
	}

	@Test
	@SuppressWarnings("EqualsWithItself")
	public void equals_sameObject_returnTrue() {
		assertThat(first.equals(first)).isTrue();
	}

	@Test
	@SuppressLint("UseValueOf")
	@SuppressWarnings({"EqualsBetweenInconvertibleTypes", "UnnecessaryBoxing"})
	public void equals_notInstance_returnFalse() {
		assertThat(first.equals(new Integer(0))).isFalse();
	}

	@Test
	public void equals_allFieldsEqual_returnTrue() {
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equals_firstLargeImageUrlNotEqual_returnFalse() {
		first.setLargeImageUrl("not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstLargeImageUrlNull_returnFalse() {
		first.setLargeImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondLargeImageUrlNull_returnFalse() {
		second.setLargeImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothLargeImageUrlsNull_returnTrue() {
		first.setLargeImageUrl(null);
		second.setLargeImageUrl(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equal_firstExtraLargeImageUrlNotEqual_returnFalse() {
		first.setExtraLargeImageUrl("not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstExtraLargeImageUrlNull_returnFalse() {
		first.setExtraLargeImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondExtraLargeImageUrlNull_returnFalse() {
		second.setExtraLargeImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothExtraLargeImageUrlsNull_returnTrue() {
		first.setExtraLargeImageUrl(null);
		second.setExtraLargeImageUrl(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equal_firstMegaImageUrlNotEqual_returnFalse() {
		first.setMegaImageUrl("not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstMegaImageUrlNull_returnFalse() {
		first.setMegaImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondMegaImageUrlNull_returnFalse() {
		second.setMegaImageUrl(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothMegaImageUrlsNull_returnTrue() {
		first.setMegaImageUrl(null);
		second.setMegaImageUrl(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void hashCode_allFieldsEqual_equalHashCodes() {
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullLargeImageUrl_equalHashCodes() {
		first.setLargeImageUrl(null);
		second.setLargeImageUrl(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullExtraLargeImageUrl_equalHashCodes() {
		first.setExtraLargeImageUrl(null);
		second.setExtraLargeImageUrl(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullMegaImageUrl_equalHashCodes() {
		first.setMegaImageUrl(null);
		second.setMegaImageUrl(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test
	public void toString_returnCorrectToString() {
		String infoAsString =
				"LastfmArtistInfo(" +
				"largeImageUrl=large_image_url, " +
				"extraLargeImageUrl=extra_large_image_url, " +
				"megaImageUrl=mega_image_url)";
		assertThat(first.toString()).isEqualTo(infoAsString);
	}

	private LastfmArtistInfo createArtistInfo() {
		LastfmArtistInfo info = new LastfmArtistInfo();
		info.setLargeImageUrl("large_image_url");
		info.setExtraLargeImageUrl("extra_large_image_url");
		info.setMegaImageUrl("mega_image_url");
		return info;
	}
}
