/**
 * Author: Alexander Hill
 *
 * This class handles the analysis of the canvas.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
