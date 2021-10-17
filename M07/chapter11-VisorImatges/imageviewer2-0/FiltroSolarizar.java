import java.awt.*;
import java.io.IOException;

public class FiltroSolarizar extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public FiltroSolarizar(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) throws IOException {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixel = image.getPixel(x, y);
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                if (pixel.getRed() < 128) {
                    red = 255-red;
                }
                if (pixel.getGreen() < 128) {
                    green = 255-green;
                }
                if (pixel.getBlue() < 128) {
                    blue = 255-blue;
                }
                image.setPixel(x, y, new Color(red, green, blue));
            }
        }
    }
}
