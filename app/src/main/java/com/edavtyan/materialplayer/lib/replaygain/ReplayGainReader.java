package com.edavtyan.materialplayer.lib.replaygain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.TagField;

import java.io.File;
import java.util.Iterator;

public class ReplayGainReader {
	public ReplayGainData read(String path) {
		double trackRG = 0;
		double albumRG = 0;

		try {
			AudioFile f = AudioFileIO.read(new File(path));
			Iterator<TagField> fields = f.getTag().getFields();

			while (fields.hasNext()) {
				TagField field = fields.next();
				if (field.toString().toLowerCase().contains("replaygain_track_gain")) {
					trackRG = parseRG(field.toString());
				}

				if (field.toString().toLowerCase().contains("replaygain_album_gain")) {
					albumRG = parseRG(field.toString());
				}
			}
		} catch (Exception ignored) {}

		return new ReplayGainData(trackRG, albumRG);
	}

	private double parseRG(String rgRaw) {
		int rgStart = 43;
		int rgEnd = rgRaw.indexOf(" dB");
		double rg = Double.parseDouble(rgRaw.substring(rgStart, rgEnd));
		return rg;
	}
}
