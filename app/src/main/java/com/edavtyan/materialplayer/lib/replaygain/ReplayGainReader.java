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
		Double trackPeak = null;
		Double albumPeak = null;

		try {
			AudioFile f = AudioFileIO.read(new File(path));
			Iterator<TagField> fields = f.getTag().getFields();

			while (fields.hasNext()) {
				TagField field = fields.next();
				String fieldStr = field.toString().toLowerCase();
				String fieldId = field.getId().toLowerCase();

				if (fieldStr.contains("replaygain_track_gain")) {
					trackRG = parseRgMp3(fieldStr);
				}

				if (fieldStr.contains("replaygain_track_peak")) {
					trackPeak = parsePeakMp3(fieldStr);
				}

				if (fieldId.contains("replaygain_track_gain")) {
					trackRG = parseRgM4a(fieldStr);
				}

				if (fieldId.contains("replaygain_track_peak")) {
					trackPeak = parsePeakM4a(fieldStr);
				}

				if (fieldStr.contains("replaygain_album_gain")) {
					albumRG = parseRgMp3(fieldStr);
				}

				if (fieldStr.contains("replaygain_album_peak")) {
					albumPeak = parsePeakMp3(fieldStr);
				}

				if (fieldId.contains("replaygain_album_gain")) {
					albumRG = parseRgM4a(fieldStr);
				}

				if (fieldId.contains("replaygain_album_peak")) {
					albumPeak = parsePeakM4a(fieldStr);
				}
			}
		} catch (Exception ignored) {}

		return new ReplayGainData(trackRG, albumRG, trackPeak, albumPeak);
	}

	private double parseRgMp3(String rgRaw) {
		int rgStart = 43;
		int rgEnd = rgRaw.indexOf(" db");
		double rg = Double.parseDouble(rgRaw.substring(rgStart, rgEnd));
		return rg;
	}

	private double parseRgM4a(String rgRaw) {
		return Double.parseDouble(rgRaw.replace(" db", ""));
	}

	private double parsePeakMp3(String peakRaw) {
		int peakStart = peakRaw.indexOf("text=") + 6;
		int peakEnd = peakStart + 8;
		return Double.parseDouble(peakRaw.substring(peakStart, peakEnd));
	}

	private double parsePeakM4a(String peakRaw) {
		return Double.parseDouble(peakRaw);
	}
}
