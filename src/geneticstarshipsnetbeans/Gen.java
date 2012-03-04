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
    float colorR = 27, colorG = 89, colorB = 76;
    float fertileAge = 7;
    float radius;

    void mutate() {
        if(Math.random() < 0.9) {
            speed += randrange(-0.1f, 0.1f);
            if(speed < 0) {
                speed = 0;
            }
        }
        if(Math.random() < 0.8) {
            maxhp += randrange(-1, 1);
            if(maxhp < 0) {
                maxhp = 0.0001f;
            }
        }
        if(Math.random() < 0.6) {
            power += randrange(-0.1f, 0.1f);
            if(power < 0) {
                power = 0;
            }
        }
        if(Math.random() < 0.1) {
            cannon_count += randrange(-2, 2);
            if(cannon_count < 0) {
                cannon_count = 0;
            }
        }
        if(Math.random() < 0.9) {
            fertileAge += randrange(-1, 1);
            if(fertileAge < 0) {
                fertileAge = 0;
            }
        }

        if(Math.random() < 0.7) {
            colorR += randrange(-30, 30);
            colorG += randrange(-30, 30);
            colorB += randrange(-30, 30);
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
        resetSize();
    }

    private void resetSize() {
        radius = (float) ((800 + 0.1 * Math.sqrt((maxhp + power + cannon_count * 0.3 + fertileAge * 0.5) / speed)) * 0.005);
    }

    float randrange(float left, float right) {
        assert (left < right);
        return (float) (Math.random()*(right - left) + left);
    }
}
