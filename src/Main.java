import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import CreatingImage.BlankImage;
import CreatingImage.PopulateImage;
import ReadConf.GetSetConfValues;

public class Main {
    public static void main(String[] args) throws Exception {
        // create dirs.
        String dirlocal = System.getProperty("user.dir");
        String dirConfFile = dirlocal + "/src/Marilyn.conf";
        String dirimageBefore = dirlocal + "/imageBefore";
        String dirimageUsed = dirlocal + "/imageUsed";
        String dirimageAfter = dirlocal + "/imageAfter";

        File filecreate;
        filecreate = new File(dirimageBefore);
        filecreate.mkdir();
        filecreate = new File(dirimageUsed);
        filecreate.mkdir();
        filecreate = new File(dirimageAfter);
        filecreate.mkdir();

        // check folder.
        String imageName = checkimageBeforeDir(dirimageBefore);

        // check configuration file.
        GetSetConfValues confValues = new GetSetConfValues(dirConfFile);

        // creat a bufferimage from the image.
        File file = new File(dirimageBefore + "/" + imageName);
        BufferedImage imageBufTL = ImageIO.read(file);
        BufferedImage imageBufTR = ImageIO.read(file);
        BufferedImage imageBufDL = ImageIO.read(file);
        BufferedImage imageBufDR = ImageIO.read(file);

        // create blank image.
        String dirAFTERImage = dirimageAfter + "/" + imageName.split(".jpg")[0] + "AFTER.jpg";
        BlankImage blankImage = new BlankImage(imageBufTL.getWidth() * 2, imageBufTL.getHeight() * 2, dirAFTERImage);
        BufferedImage blankImageBuf = blankImage.createBlankImage();

        // create threads.
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future<BufferedImage> futureTL = service.submit(new PopulateImage(confValues.getTL(), imageBufTL));
        Future<BufferedImage> futureTR = service.submit(new PopulateImage(confValues.getTR(), imageBufTR));
        Future<BufferedImage> futureDL = service.submit(new PopulateImage(confValues.getDL(), imageBufDL));
        Future<BufferedImage> futureDR = service.submit(new PopulateImage(confValues.getDR(), imageBufDR));

        BufferedImage imageTL = futureTL.get();
        BufferedImage imageTR = futureTR.get();
        BufferedImage imageDL = futureDL.get();
        BufferedImage imageDR = futureDR.get();

        // write into blank image
        for (int y = 0; y < imageTL.getHeight(); y++) {
            for (int x = 0; x < imageTL.getWidth(); x++) {
                int pixel = imageTL.getRGB(x, y);
                Color colorTL = new Color(pixel, true);
                blankImageBuf.setRGB(x, y, colorTL.getRGB());
            }
        }
        for (int y = 0; y < imageTR.getHeight(); y++) {
            for (int x = 0; x < imageTR.getWidth(); x++) {
                int pixel = imageTR.getRGB(x, y);
                Color colorTR = new Color(pixel, true);
                blankImageBuf.setRGB(x + imageTR.getWidth(), y, colorTR.getRGB());
            }
        }
        for (int y = 0; y < imageDL.getHeight(); y++) {
            for (int x = 0; x < imageDL.getWidth(); x++) {
                int pixel = imageDL.getRGB(x, y);
                Color colorDL = new Color(pixel, true);
                blankImageBuf.setRGB(x, y + imageDL.getHeight(), colorDL.getRGB());
            }
        }
        for (int y = 0; y < imageDR.getHeight(); y++) {
            for (int x = 0; x < imageDR.getWidth(); x++) {
                int pixel = imageDR.getRGB(x, y);
                Color colorDR = new Color(pixel, true);
                blankImageBuf.setRGB(x + imageDR.getWidth(), y + imageDR.getHeight(), colorDR.getRGB());
            }
        }

        // create image new file.
        blankImage.buildImage(resizeImage(blankImageBuf, imageBufTL.getWidth(), imageBufTL.getHeight()));

        // move image to used directory.
        moveAndRename(dirimageBefore, dirimageUsed, imageName);
        System.exit(1);

    }

    // check the imageBefor dir for jpg file.
    public static String checkimageBeforeDir(String dir) throws Exception {
        String[] files;
        File file = new File(dir);
        files = file.list();
        if (files.length != 1) {
            throw new Exception(
                    "The imageBefore directorty should have only one file of and image" + " with .jpg ending.");
        } else if (!(files[0].endsWith(".jpg"))) {
            throw new Exception("The imageBefore directorty should have file of and image with .jpg ending.");
        } else {
            return files[0];
        }
    }

    // move file.
    public static void moveAndRename(String dirFrom, String dirTo, String imageName) {
        File fileBefore = new File(dirFrom + "/" + imageName);
        fileBefore.renameTo(new File(dirTo + "/" + imageName));
    }

    // resize image to original size.
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
            throws Exception {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
