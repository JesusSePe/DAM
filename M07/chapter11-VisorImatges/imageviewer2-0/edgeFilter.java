import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class edgeFilter extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public edgeFilter(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) throws IOException {
        ThresholdFilter tf = new ThresholdFilter("tf");
        FiltroInvertir fi = new FiltroInvertir("fi");
        tf.apply(image);
        fi.apply(image);
        int height = image.getHeight();
        int width = image.getWidth();
        OFImage output = new OFImage(image);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Color pixel = image.getPixel(x, y);
                if (pixel.getRGB() >= Color.GRAY.getRGB()) {
                    output.setRGB(x, y, Color.white.getRGB());
                } else {
                    output.setRGB(x, y, Color.black.getRGB());
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setPixel(x, y, output.getPixel(x, y));
            }
        }
    }
}
