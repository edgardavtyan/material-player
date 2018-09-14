package com.edavtyan.materialplayer.lib.replaygain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.TagField;

import java.io.File;
import java.util.Iterator;

public class ReplayGainReader {
	public double read(String path) throws ReplayGainNotFoundException {
		try {
			AudioFile f = AudioFileIO.read(new File(path));
			Iterator<TagField> fields = f.getTag().getFields();

			String rgRaw = "";
			while (fields.hasNext()) {
				TagField field = fields.next();
				if (field.toString().toLowerCase().contains("replaygain_track_gain")) {
					rgRaw = field.toString();
					break;
				}
			}

			if (rgRaw.isEmpty()) {
				throw new ReplayGainNotFoundException();
			}

			int rgStart = 43;
			int rgEnd = rgRaw.indexOf(" dB");
			double rg = Double.parseDouble(rgRaw.substring(rgStart, rgEnd));
			return rg;
		} catch (Exception ignored) {}

		return 0;
	}
}
