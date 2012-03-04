package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class EventSpeedX extends Event {

    EventSpeedX(int x) {
        this.x = x;
    }
    final int x;

    @Override
    void run() {
        Game.getSingleton().iterationCountPerFrame = x;
    }
}
