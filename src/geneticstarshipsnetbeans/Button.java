package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class Button {

    Button(String txt, float x, float y, Event pressAction) {
        super();
        this.x = x;
        this.y = y;
        this.txt = txt;
        this.pressAction = pressAction;
    }

    void Update() {
        isHovered = Game.getSingleton().mouseX > x
                && Game.getSingleton().mouseY > y
                && Game.getSingleton().mouseX < x + w
                && Game.getSingleton().mouseY < y + h;
        boolean wasPressed = isPressed;
        isPressed = isHovered && Game.getSingleton().mousePressed;
        if(wasPressed && !isPressed) {
            pressAction.run();
        }
    }

    void Draw() {
        if(isHovered) {
            if(isPressed) {
                Game.getSingleton().fill(120, 120, 120);
            } else {
                Game.getSingleton().fill(150, 150, 150);
            }
        } else {
            Game.getSingleton().fill(190, 190, 190);
        }
        Game.getSingleton().rect(x, y, w, h);
        Game.getSingleton().fill(50, 50, 50);
        Game.getSingleton().text(txt, x + 5, y + 13);
    }
    final float w = 50, h = 15, x, y;
    String txt;
    boolean isPressed = false;
    boolean isHovered;
    Event pressAction;
}
