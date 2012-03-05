package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public abstract class Actor {

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

    protected abstract void Update();
    protected abstract void Draw();
    
    private static Actor[] actors = new Actor[2048 * 2048];
    private static int actorCount = 0;
    protected boolean isDead = false;
}
