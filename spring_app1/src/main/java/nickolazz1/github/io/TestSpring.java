package nickolazz1.github.io;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//import java.util.ArrayList;
//import java.util.List;

//refactor for linux env
public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getName());

        //Music music = context.getBean("musicBean", Music.class);
        //MusicPlayer musicPlayer = new MusicPlayer(music);
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();


        String [] testplaylist = {"Krasnaya plesen", "sektor gaza"};
        musicPlayer.setMusicList(testplaylist);
        musicPlayer.playMusicList();


        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        context.close();
    }
}
