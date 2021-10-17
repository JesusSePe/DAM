import java.awt.*;

public class FiltroInvertir extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public FiltroInvertir(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixel = image.getPixel(x, y);
                image.setPixel(x, y, new Color(255-pixel.getRed(), 255-pixel.getBlue(), 255-pixel.getGreen()));
            }
        }
    }
}
