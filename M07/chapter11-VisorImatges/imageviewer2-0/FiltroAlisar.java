import java.awt.*;
import java.util.ArrayList;

public class FiltroAlisar extends Filter{
    /**
     * Create a new filter with a given name.
     *
     * @param name The name of the filter.
     */
    public FiltroAlisar(String name) throws ArrayIndexOutOfBoundsException{
        super(name);
    }

    @Override
    public void apply(OFImage image){
        // First, we get a copy of the image
        OFImage copy = new OFImage(image);
        int height = image.getHeight();
        int width = image.getWidth();
        ArrayList<Color> colors = new ArrayList<>();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                // Surrounding pixels
                try {
                    colors.add(image.getPixel(x-1, y-1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x, y-1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x+1, y-1));
                } catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x-1, y));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x+1, y));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x-1, y+1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x, y+1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    colors.add(image.getPixel(x+1, y+1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Our pixel
                Color pixel = image.getPixel(x, y);
                for (int i = 0; i <= 8; i++) {
                    colors.add(pixel);
                }
                int averageR = pixel.getRed();
                int averageG = pixel.getGreen();
                int averageB = pixel.getBlue();
                for (Color color : colors) {
                    averageR += color.getRed();
                    averageG += color.getGreen();
                    averageB += color.getBlue();
                }
                Color average = new Color(averageR/(colors.size()+1), averageG/(colors.size()+1) , averageB/(colors.size()+1));
                copy.setPixel(x, y, average);
            }
        }
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                copy.setPixel(x, y, copy.getPixel(x,y));
            }
        }
    }
}
