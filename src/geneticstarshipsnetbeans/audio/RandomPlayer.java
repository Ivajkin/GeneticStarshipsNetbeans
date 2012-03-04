package geneticstarshipsnetbeans.audio;

import ddf.minim.*;

/**
 *
 * @author Tim
 */
public class RandomPlayer {

    RandomPlayer(Minim minim, int count, String name) {
        this.count = count;
        sounds = new AudioPlayer[count];
        for(int i = 0; i < count; ++i) {
            sounds[i] = minim.loadFile(name + (i + 1) + ".wav");
        }
    }

    void play() {

        float r = (float) Math.random();
        for(int i = 0; i < count; ++i, r -= 1.0 / count) {
            if(r < 0) {
                sounds[i].rewind();
                sounds[i].play();
                break;
            }
        }
    }

    void close() {
        for(int i = 0; i < count; ++i) {
            sounds[i].close();
        }
    }
    private AudioPlayer[] sounds;
    private final int count;
}