package com.reis.game.audio;

import com.badlogic.gdx.audio.Music;
import com.reis.game.resource.ResourceManager;
import com.reis.game.util.FadeDirection;

/**
 * Created by bernardoreis on 11/20/16.
 */

public final class SoundManager {

    private static final float FADE_INC = 0.01f;

    private static MidiPlayer midiPlayer;
    private static Music currentSong;

    private SoundManager() {}

    public static void playSong(String name) {
        playSong(name, true);
    }

    public static void playSong(String name, boolean looping) {
        Music song = ResourceManager.getSong(name);
        playSong(song);
    }

    public static void playSong(Music music) {
        playSong(music, true);
    }

    public static void playSong(Music music, boolean looping) {
        if (currentSong != null)
            currentSong.stop();
        currentSong = music;
        currentSong.setLooping(looping);
        currentSong.play();
    }

    public static void fadeSong(Music music, FadeDirection direction, float fadeTime) {

    }

    public static MidiPlayer getMidiPlayer() {
        return midiPlayer;
    }

    public static void setMidiPlayer(MidiPlayer midiPlayer) {
        SoundManager.midiPlayer = midiPlayer;
    }

    public static Music getCurrentSong() {
        return currentSong;
    }
}
