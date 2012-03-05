package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
class FoodCell extends Actor {

    FoodCell(float x, float y, float foodAmount) {
        this.foodAmount = foodAmount;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void Update() {
        double foodGrownPerPixel = growPerIterationAmount
                + Math.random() * growPerIterationRandomAmount;
        foodAmount += foodGrownPerPixel * cellWidth * cellWidth;
        if(foodAmount < 0) {
            foodAmount = 0;
        } else if(foodAmount > maxFoodAmount) {
            foodAmount = maxFoodAmount;
        }
    }

    @Override
    protected void Draw() {
        Game.getSingleton().fill(134, foodAmount * 207, 89);
        Game.getSingleton().rect(x, y, cellWidth, cellWidth);//background(134, 207, 89);
    }

    float getFood() {
        float amountFeed = 0.01f;
        foodAmount -= amountFeed;
        return amountFeed*foodAmount*foodAmount*foodAmount;
    }
    static final float cellWidth = 50;
    // Должен быть от 0 до 1.
    private float foodAmount;
    private float x, y;
    // Количество выросшей еды за итерацию.
    private final float growPerIterationAmount = 0.5f;
    private final float growPerIterationRandomAmount = 0.05f;
    private final float maxFoodAmount = 1;
}
