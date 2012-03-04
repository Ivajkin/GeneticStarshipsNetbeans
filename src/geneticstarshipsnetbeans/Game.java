package geneticstarshipsnetbeans;

import geneticstarshipsnetbeans.audio.Sounds;
import processing.core.PApplet;
import processing.core.PFont;
//import processing.opengl.*;

/**
 *
 * @author Tim
 */
@SuppressWarnings("serial")
public class Game extends PApplet {

    static private Game singleton;

    static Game getSingleton() {
        return singleton;
    }

    public Game() {
        singleton = this;
    }

    @Override
    public void setup() {
        size(1366, 768, P3D);

        Sounds.Initialise(this);

        Gen gen;
        for(int i = 0; i < 50; ++i) {
            gen = new Gen();

            for(int mcount = 0; mcount < 100; ++mcount) {
                gen.mutate();
            }
            float x = (random(1) > 0.5
                    ? sqrt(random(width / 2))
                    : width - sqrt(random(width / 2)));
            float y = (random(1) > 0.5
                    ? sqrt(random(height / 2))
                    : height - sqrt(random(height / 2)));
            Ship.createShip(gen, x, y);
        }
        PFont font;
        font = loadFont("BodoniMT-BoldItalic-15.vlw");
        textFont(font);

        Button speed1x = new Button("1X", 10, 10, new EventSpeedX(1));
        Button speed5x = new Button("5X", 70, 10, new EventSpeedX(5));
        Button speed20x = new Button("20X", 130, 10, new EventSpeedX(20));
        Button speed100x = new Button("100X", 190, 10, new EventSpeedX(100));
    }

    @Override
    public void draw() {
        background(134, 207, 89);


        for(int iter = 0; iter < Game.getSingleton().iterationCountPerFrame; ++iter) {
            Ship.UpdateAll();
            Actor.UpdateAll();
        }
        Ship.DrawAll();
        Actor.DrawAll();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{geneticstarshipsnetbeans.Game.class.getName()});
    }
    int iterationCountPerFrame = 5;
}