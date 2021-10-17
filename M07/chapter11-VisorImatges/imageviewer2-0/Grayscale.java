import java.awt.*;

public class Grayscale extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public Grayscale(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixel = image.getPixel(x, y);
                int brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                image.setPixel(x, y, new Color(brightness));
            }
        }
    }
}
