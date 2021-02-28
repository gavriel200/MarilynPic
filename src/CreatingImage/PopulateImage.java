package CreatingImage;

import ReadConf.Value;

import java.util.concurrent.Callable;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class PopulateImage implements Callable<BufferedImage> {
    private Value value;
    private BufferedImage bufimage;

    public PopulateImage(Value value, BufferedImage bufimage) {
        this.value = value;
        this.bufimage = bufimage;
    }

    @Override
    public BufferedImage call() throws Exception {
        for (int y = 0; y < this.bufimage.getHeight(); y++) {
            for (int x = 0; x < this.bufimage.getWidth(); x++) {

                // Retrieving contents of a pixel
                int pixel = this.bufimage.getRGB(x,y);

                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);

                // Retrieving the R G B values and set
                int red = setRED(color.getRed());
                int green = setGREEN(color.getGreen());
                int blue = setBLUE(color.getBlue());

                // create new color object
                Color newcolor = new Color(red, green, blue);

                // Setting new Color object to the image
                this.bufimage.setRGB(x, y, newcolor.getRGB());
            }
        }
        return this.bufimage;
    }

    // set red color from configuration
    public int setRED(int red) {
        switch(this.value.getREDtype()) {
            case "%":
                if (this.value.getREDvalue() > 50) {
                    red = (int) red + (255 - red)/50*(this.value.getREDvalue()-50);
                } else if (this.value.getREDvalue() < 50) {
                    red = (int) red - red/50*(50 - this.value.getREDvalue());
                }
                break;
            case "RGB":
                red = this.value.getREDvalue();
                break;
            default:
                break;
        }
        return red;
    }

    // set green color from configuration
    public int setGREEN(int green) {
        switch(this.value.getGREENtype()) {
            case "%":
                if (this.value.getGREENvalue() > 50) {
                    green = green + (255 - green)/50*(this.value.getGREENvalue()-50);
                } else if (this.value.getGREENvalue() < 50) {
                    green = green - green/50*(50 - this.value.getGREENvalue());
                }
                break;
            case "RGB":
                green = this.value.getGREENvalue();
                break;
            default:
                break;
        }
        return green;
    }

    // set blue color from configuration
    public int setBLUE(int blue) {
        switch(this.value.getBLUEtype()) {
            case "%":
                if (this.value.getBLUEvalue() > 50) {
                    blue = blue + (255 - blue)/50*(this.value.getBLUEvalue()-50);
                } else if (this.value.getBLUEvalue() < 50) {
                    blue = blue - blue/50*(50 - this.value.getBLUEvalue());
                }
                break;
            case "RGB":
                blue = this.value.getBLUEvalue();
                break;
            default:
                break;
        }
        return blue;
    }
}