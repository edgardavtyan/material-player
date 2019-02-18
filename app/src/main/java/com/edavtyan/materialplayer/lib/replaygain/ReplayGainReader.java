package com.edavtyan.materialplayer.lib.replaygain;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.TagField;

import java.io.File;
import java.util.Iterator;

public class ReplayGainReader {

	public static final String RG_TRACK_GAIN = "replaygain_track_gain";
	public static final String RG_TRACK_PEAK = "replaygain_track_peak";
	public static final String RG_ALBUM_GAIN = "replaygain_album_gain";
	public static final String RG_ALBUM_PEAK = "replaygain_album_peak";

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
				if (fieldStr.contains(RG_TRACK_GAIN)) trackRG = parseRgMp3(fieldStr);
				if (fieldStr.contains(RG_TRACK_PEAK)) trackPeak = parsePeakMp3(fieldStr);
				if (fieldId.contains(RG_TRACK_GAIN)) trackRG = parseRgM4a(fieldStr);
				if (fieldId.contains(RG_TRACK_PEAK)) trackPeak = parsePeakM4a(fieldStr);
				if (fieldStr.contains(RG_ALBUM_GAIN)) albumRG = parseRgMp3(fieldStr);
				if (fieldStr.contains(RG_ALBUM_PEAK)) albumPeak = parsePeakMp3(fieldStr);
				if (fieldId.contains(RG_ALBUM_GAIN)) albumRG = parseRgM4a(fieldStr);
				if (fieldId.contains(RG_ALBUM_PEAK)) albumPeak = parsePeakM4a(fieldStr);
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
