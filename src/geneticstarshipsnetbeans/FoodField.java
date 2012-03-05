
package geneticstarshipsnetbeans;

/**
 *
 * @author Tim
 */
public class FoodField {

    FoodField() {
        for(int i = 0; i < cellCountX; ++i) {
            for(int j = 0; j < cellCountY; ++j) {
                float foodAmount = (float) Math.random();
                float x = i*FoodCell.cellWidth,
                        y = j*FoodCell.cellWidth;
                cells[i + j*cellCountX] = new FoodCell(x, y, foodAmount);
            }
        }
    }
    FoodCell getFoodCell(float x, float y) {
        assert(x >= 0);
        assert(x <= cellCountX*FoodCell.cellWidth);
        assert(y >= 0);
        assert(y <= cellCountY*FoodCell.cellWidth);
        
        int xi = (int) Math.floor(x/FoodCell.cellWidth),
                yi = (int) Math.floor(y/FoodCell.cellWidth);
        return cells[xi+yi*cellCountX];
    }
    
    private final int cellCountX = 26;
    private final int cellCountY = 14;
    private FoodCell[] cells = new FoodCell[cellCountX*cellCountY];
}
