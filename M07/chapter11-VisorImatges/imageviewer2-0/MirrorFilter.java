import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.IOException;

public class MirrorFilter extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public MirrorFilter(String name) {
        super(name);
    }

    @Override
    public void apply(OFImage image) throws IOException {
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage flipped = new BufferedImage(width, height, image.getType());
        Graphics2D g = flipped.createGraphics();
        g.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixel = new Color(flipped.getRGB(x, y));
                image.setPixel(x, y, pixel);
            }
        }
    }
}
