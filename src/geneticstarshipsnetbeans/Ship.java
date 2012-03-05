package geneticstarshipsnetbeans;

import geneticstarshipsnetbeans.audio.Sounds;

/**
 *
 * @author Tim
 */
public class Ship {

    static void UpdateAll() {
        
        livingShipCount = 0;
        for(int i = 0; i < shipCount; ++i) {
            if(!ships[i].isDead) {
                ++livingShipCount;
            }
        }

        for(int i = 0; i < shipCount; ++i) {
            if(!ships[i].isDead) {
                ships[i].Update();
            }
        }

        if(Game.getSingleton().mousePressed) {
            for(int i = 0; i < shipCount; ++i) {
                if(!ships[i].isDead) {
                    float rangeX = ships[i].x - Game.getSingleton().mouseX;
                    float rangeY = ships[i].y - Game.getSingleton().mouseY;
                    float range = rangeX * rangeX + rangeY * rangeY;
                    ships[i].hp -= 1500 / (range + 4);
                }
            }
        }
    }

    static void DrawAll() {
        
        for(int i = 0; i < shipCount; ++i) {
            if(!ships[i].isDead) {
                ships[i].Update();
                ships[i].Draw();
            }
        }
    }

    Ship(Gen gen, float x, float y) {
        this.gen = gen;
        this.x = x;
        this.y = y;
        this.hp = gen.maxhp;
        turrets = new RocketTurret[gen.cannon_count];
        for(int i = 0; i < gen.cannon_count; ++i) {
            turrets[i] = new RocketTurret(this);
        }

        Sounds.playShipBorn();
    }

    void Draw() {
        Game.getSingleton().fill(gen.colorR, gen.colorG, gen.colorB);
        //ellipse(x, y, gen.radius, gen.radius);
        float r = gen.radius;
        // Фертильность шар
        Game.getSingleton().ellipse(x, y, r * fertile * 0.0005f, r * fertile * 0.0005f);
        //rect(x-r/2, y-r/2, r, r);
        Game.getSingleton().ellipse(x, y, r, r);

        Game.getSingleton().fill(37, 89, 76);
        Game.getSingleton().rect(x - 4, y + 10, 8, 2);
        Game.getSingleton().fill(255, 0, 0);
        Game.getSingleton().rect(x - 4, y + 10, 8 - (hp / gen.maxhp) * 8, 2);

        /*for(int i = 0; i < gen.cannon_count; ++i) {
            turrets[i].Draw();
        }*/
    }

    private void UpdateLifeCycle() {
        // Здоровье - отражает также старение.
        // Если кораблей очень много - штарф по здоровью (они болеют).
        if(livingShipCount < 300) {
            // Нормальное старение.
            hp -= 0.04f;
            fertile += 0.53f;
        } else if(livingShipCount < 800) {
            // Средний штраф.
            hp -= 0.08f;
            fertile += 0.33f;
        } else {
            // Высокий штраф.
            hp *= 0.95f;
            hp -= 0.08f + Math.random()*0.2f + Math.random()*0.6f;
            fertile += 0.2f + Math.random()*0.05f;
        }
        
        // Очень большие корабли штрафуем.
        if(gen.radius > 13) {
            hp -= 0.03f;
        }
        // Слишком быстрые штрафуем.
        if(gen.speed > 1) {
            hp -= 0.03f;
        }
    }
    void Update() {
        hp += food.getFoodCell(x, y).getFood()*100;
        UpdateLifeCycle();
        if(hp <= 0) {
            isDead = true;
            Sounds.playShipExplosion();
            return;
        }
        
        double vel = Math.random() * gen.speed;
        double a = Math.random() * 2 * Math.PI;
        vx *= 0.98f;
        vy *= 0.98f;
        vx += vel * Math.cos(a);
        vy += vel * Math.sin(a);

        x += vx;
        y += vy;

        final int maxLivingAmount = 10000;
        if(livingShipCount < maxLivingAmount) {
            for(int i = 0; i < shipCount; ++i) {
                if(!ships[i].isDead
                        && ships[i] != this
                        && isCollideWith(ships[i])) {

                    //System.out.println("fertile: " + ships[i].fertile);
                    if((ships[i].gen.fertileAge
                            - ships[i].fertile)
                            < 0.001
                            && (this.gen.fertileAge - this.fertile) < 0.001) {
                        // Обнуляем способность к воспроизведению.
                        ships[i].fertile = 0;
                        this.fertile = 0;
                        // Ослабляем после воспроизведения.
                        ships[i].hp *= 0.8f;
                        this.hp *= 0.8f;
                        Create(ships[i], this);
                    }
                }
            }
        }


        // Collide with borders
        if(x < 0) {
            x = 0;
            vx = -vx;
        } else if(x > Game.getSingleton().width) {
            x = Game.getSingleton().width;
            vx = -vx;
        }
        if(y < 0) {
            y = 0;
            vy = -vy;
        } else if(y > Game.getSingleton().height) {
            y = Game.getSingleton().height;
            vy = -vy;
        }
        /*for(int i = 0; i < gen.cannon_count; ++i) {
            turrets[i].Update();
        }*/
    }

    boolean isEnemy(Ship sec) {
        float rangeR = sec.gen.colorR - this.gen.colorR;
        float rangeG = sec.gen.colorG - this.gen.colorG;
        float rangeB = sec.gen.colorB - this.gen.colorB;
        float range = rangeR * rangeR + rangeG * rangeG + rangeB * rangeB;
        return (range > 132);
    }
    
    void Create(Ship parentA, Ship parentB) {
        if(parentA.isDead || parentB.isDead) {
            return;
        }

        Gen mutatedGen = new Gen();
        mutatedGen.speed = (parentA.gen.speed + parentB.gen.speed) / 2;
        mutatedGen.maxhp = (parentA.gen.maxhp + parentB.gen.maxhp) / 2;
        mutatedGen.power = (parentA.gen.power + parentB.gen.power) / 2;
        //gen.radius = (parentA.gen.radius + parentB.gen.radius)/2;
        mutatedGen.cannon_count = (parentA.gen.cannon_count + parentB.gen.cannon_count) / 2;
        mutatedGen.mutate();

        ships[shipCount++] = new Ship(mutatedGen,
                (float)((parentA.x + parentB.x) / 2 + Math.random()*10),
                (float)((parentA.y + parentB.y) / 2 + Math.random()*10));
    }
    static void createShip(Gen gen, float x, float y) {
        ships[shipCount++] = new Ship(gen, x, y);
    }
    Gen gen = new Gen();
    float x, y;
    float vx = 0, vy = 0;
    float fertile = 0;
    boolean isDead = false;
    float hp;
    RocketTurret[] turrets;
    
    private static int livingShipCount;
    private static int shipCount;
    private static FoodField food = new FoodField();
    private static Ship[] ships = new Ship[2048 * 2048];

    private boolean isCollideWith(Ship ship) {
        return Math.abs(ship.x - x)
                    + Math.abs(ship.y - y)
                    < ship.gen.radius + this.gen.radius;
    }
}