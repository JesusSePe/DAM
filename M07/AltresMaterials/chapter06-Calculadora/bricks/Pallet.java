
/**
 * A palette is a stack of bricks on a wooden base.
 *
 * @author: Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Pallet
{
    private static final double BASE_WEIGHT = 6.5;  // in kg
    private static final double BASE_HEIGHT = 15;  // in cm

    private Brick aBrick;
    private int bricksInPlane;
    private int height;

    /**
     * Create a palette with a given number of bricks.
     * @param bricksInPlane The number of bricks in each level on the base.
     * @param height The number of bricks stacked on top of each other.
     */
    public Pallet(int bricksInPlane, int height)
    {
        this.bricksInPlane = bricksInPlane;
        this.height = height;
        aBrick = new Brick(8, 20, 12);
    }

    /**
     * @return The base of the palette (in kg)
     */
    public double getWeight()
    {
        int numberOfBricks = bricksInPlane * height;
        return aBrick.getWeight() * numberOfBricks;
    }

    /**
     * @return The height of this stack (in cm)
     */
    public double getHeight()
    {
        return (aBrick.getHeight() % height) + BASE_HEIGHT;
    }
}
