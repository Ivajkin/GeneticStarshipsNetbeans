package geneticstarshipsnetbeans.audio;

import ddf.minim.*;

/**
 *
 * @author Tim
 */
public class Sounds {

    public static void Initialise(geneticstarshipsnetbeans.Game game) {
        assert (Sounds.minim == null);

        Sounds.minim = new Minim(game);
        shipExplosion = minim.loadFile("explodeSmall.wav");
        shipBorn = new RandomPlayer(minim, 3, "BansheeMissileHit");
    }

    public static void Dispose() {
        assert (minim != null);

        shipBorn.close();
        shipExplosion.close();

        minim.stop();
    }

    public static void playShipExplosion() {
        Sounds.shipExplosion.rewind();
        Sounds.shipExplosion.play();
    }

    public static void playShipBorn() {
        shipBorn.play();
    }
    private static AudioPlayer shipExplosion;
    private static RandomPlayer shipBorn;
    private static Minim minim = null;
}