/**
 * Created by alex on 20/02/2019.
 */

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {

    private void canvasToImage(Component canvasPanel)
    {
        Dimension size = canvasPanel.getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        canvasPanel.paint(g2);
        try
        {
            ImageIO.write(image, "png", new File("canvasOutput.png"));
            System.out.println("Canvas saved as png.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
