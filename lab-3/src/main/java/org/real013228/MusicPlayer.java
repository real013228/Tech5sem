package org.real013228;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class MusicPlayer {
    private Music music;
    private MusicPlayer() {}
    public MusicPlayer(Music music) {
        this.music = music;
    }
    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(@Qualifier("classicalMusic") Music music) {
        this.music = music;
    }
}
