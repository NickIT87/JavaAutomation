package nickolazz1.github.io;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private Music music;
    private String[] musicList;
    private String name;
    private int volume;

    //public MusicPlayer(Music music) {this.music = music;}
    public MusicPlayer() {}

    public void setMusic(Music music) { this.music = music; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() { return this.volume; }

    public void playMusic() { System.out.println("Playing: " + music.getSong()); }

    public void setMusicList(String[] musicList) { this.musicList = musicList; }

    public void playMusicList() {
        System.out.println("DEBUG MESSAGE");
        for (String music : musicList) {
            System.out.println("Playing: " + music);
        }
    }
}
