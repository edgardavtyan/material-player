package com.edavtyan.materialplayer.app.music;

import android.content.SharedPreferences;

import java.util.HashMap;

public enum RepeatMode {
    NO_REPEAT, REPEAT, REPEAT_ONE;

    static HashMap<RepeatMode, Integer> toIntMap;
    static HashMap<Integer, RepeatMode> fromIntMap;

    static {
        toIntMap = new HashMap<>();
        toIntMap.put(NO_REPEAT, 0);
        toIntMap.put(REPEAT, 1);
        toIntMap.put(REPEAT_ONE, 2);

        fromIntMap = new HashMap<>();
        fromIntMap.put(0, NO_REPEAT);
        fromIntMap.put(1, REPEAT);
        fromIntMap.put(2, REPEAT_ONE);
    }

    public static int toInt(RepeatMode mode) {
        return toIntMap.get(mode);
    }

    public static RepeatMode fromInt(Integer mode) {
        return fromIntMap.get(mode);
    }

    public static RepeatMode fromPref(SharedPreferences prefs, String pref) {
        return fromInt(prefs.getInt(pref, toInt(NO_REPEAT)));
    }
}

