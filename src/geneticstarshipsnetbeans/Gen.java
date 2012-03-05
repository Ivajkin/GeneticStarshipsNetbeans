package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class Gen {

    Gen() {
        resetSize();
    }
    float speed = 1;
    float maxhp = 40;
    float power = 1;
    int cannon_count = 2;
    int colorR = 27, colorG = 89, colorB = 76;
    float fertileAge = 7;
    float radius;

    void mutate() {
        speed += randrange(-0.1f, 0.1f);
        // Тормозим рост скорости
        speed *= 0.998f;
        if(speed < 0) {
            speed = 0;
        }

        maxhp += randrange(-1, 1);
        if(maxhp < 0) {
            maxhp = 0.0001f;
        }

        power += randrange(-0.1f, 0.1f);
        if(power < 0) {
            power = 0;
        }

        cannon_count += randrange(-2, 2);
        if(cannon_count < 0) {
            cannon_count = 0;
        }


        fertileAge += randrange(-0.05f, 0.05f);
        if(fertileAge < 0) {
            fertileAge = 0;
        }
        mutateColor();
        resetSize();
    }

    private void mutateColor() {
        
        colorR += randrange(-60, 60);
        colorG += randrange(-60, 60);
        colorB += randrange(-60, 60);

        if(colorR < 0) {
            colorR = 0;
        }
        if(colorG < 0) {
            colorG = 0;
        }
        if(colorB < 0) {
            colorB = 0;
        }
        if(colorR > 255) {
            colorR = 255;
        }
        if(colorG > 255) {
            colorG = 255;
        }
        if(colorB > 255) {
            colorB = 255;
        }
    }

    private void resetSize() {
        radius = (float) ((800 + 0.1 * Math.sqrt((maxhp + power + cannon_count * 0.3 + fertileAge * 0.5) / speed)) * 0.005);
    }

    private float randrange(float left, float right) {
        assert (left < right);
        return (float) (Math.random()) * (right - left) + left;
    }
}
