package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class Actor {

    Actor() {
        actors[actorCount++] = this;
    }

    static void DrawAll() {
        for(int i = 0; i < actorCount; ++i) {
            if(!actors[i].isDead) {
                actors[i].Draw();
            }
        }
    }

    static void UpdateAll() {
        for(int i = 0; i < actorCount; ++i) {
            if(!actors[i].isDead) {
                actors[i].Update();
            }
        }
    }

    protected void Update() {
    }

    protected void Draw() {
    }
    private static Actor[] actors = new Actor[4096];
    private static int actorCount = 0;
    protected boolean isDead = false;
}
