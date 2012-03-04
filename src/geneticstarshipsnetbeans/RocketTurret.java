package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class RocketTurret extends Actor {

    RocketTurret(Ship owner) {
        super();
        this.owner = owner;

        range = (float) (radius + owner.gen.radius + Math.random()*3 + 3);
    }

    @Override
    protected void Update() {
        a += 0.05;
        x = (float) (owner.x + range * Math.cos(a));
        y = (float) (owner.y + range * Math.sin(a));
    }

    @Override
    protected void Draw() {
        Game.getSingleton().fill(owner.gen.colorR, owner.gen.colorG, owner.gen.colorB);
        //ellipse(x, y, radius, radius);
        Game.getSingleton().rect(x - radius / 2, y - radius / 2, radius, radius);
    }
    Ship owner;
    float vx, vy;
    float x, y;
    final float radius = 2;
    final float range;
    float a = (float) (Math.random() * Math.PI * 2);
}
