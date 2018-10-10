package com.edavtyan.materialplayer.lib.replaygain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.TagField;

import java.io.File;
import java.util.Iterator;

public class ReplayGainReader {
	public ReplayGainData read(String path) {
		Double trackRG = null;
		Double albumRG = null;

		try {
			AudioFile f = AudioFileIO.read(new File(path));
			Iterator<TagField> fields = f.getTag().getFields();

			while (fields.hasNext()) {
				TagField field = fields.next();
				if (field.toString().toLowerCase().contains("replaygain_track_gain")) {
					trackRG = parseRgMp3(field.toString());
				}

				if (field.getId().toLowerCase().contains("replaygain_track_gain")) {
					trackRG = parseRgM4a(field.toString());
				}

				if (field.toString().toLowerCase().contains("replaygain_album_gain")) {
					albumRG = parseRgMp3(field.toString());
				}

				if (field.getId().toLowerCase().contains("replaygain_album_gain")) {
					albumRG = parseRgM4a(field.toString());
				}
			}
		} catch (Exception ignored) {}

		return new ReplayGainData(trackRG, albumRG);
	}

	private double parseRgMp3(String rgRaw) {
		int rgStart = 43;
		int rgEnd = rgRaw.indexOf(" dB");
		double rg = Double.parseDouble(rgRaw.substring(rgStart, rgEnd));
		return rg;
	}

	private double parseRgM4a(String rgRaw) {
		return Double.parseDouble(rgRaw.replace(" dB", ""));
	}
}
