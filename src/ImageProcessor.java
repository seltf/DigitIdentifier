import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author: Alexander Hill
 *
 * This class handles the analysis of the canvas.
 *
 * TO DO:
 * >load image
 *
 * >analyse image with k-NN
 *
 * >return a number value
 */
public class ImageProcessor {

     BufferedImage importedImage;

    public ImageProcessor(){
        importedImage = null;
    }//end of ImageProcessor()

    public void readImage(){

        try {
            importedImage = ImageIO.read(new File("exportedImage.png"));
        } catch (Exception e) {
            System.out.println("Error importing image.");
            e.printStackTrace();
        }
    }//end of readImage()

}
