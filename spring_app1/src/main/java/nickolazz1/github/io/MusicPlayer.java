package nickolazz1.github.io;

public class MusicPlayer {
    private Music music;
    private String name;
    private int volume;

    //public MusicPlayer(Music music) {this.music = music;}
    public MusicPlayer() {}

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }
}
