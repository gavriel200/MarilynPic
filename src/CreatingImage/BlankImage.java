package CreatingImage;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BlankImage {

    private int width;
    private int height;
    private String dir;

    public BlankImage(int width, int height, String dir) {
        this.width = width;
        this.height = height;
        this.dir = dir;
    }

    // create blank bufferedimage object.
    public BufferedImage createBlankImage() {
        return new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    // write image into file.
    public void buildImage(BufferedImage img) throws IOException {
        File file4 = new File(this.dir);
        ImageIO.write(img, "jpg", file4);
    }

}
