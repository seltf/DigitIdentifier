/**
 * Author: Alexander Hill
 *
 * This class handles the canvas that the user can draw on.
 */
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Canvas extends JComponent {

    private Image canvasImage;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;

    public Canvas() {

        //add border around the canvas area
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //set starting coords when mouse is pressed
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        //update coords as mouse is dragged and draw a line between them
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                //set starting coords
                currentX = e.getX();
                currentY = e.getY();

                //set line thickness
                g2.setStroke(new BasicStroke(10));

                //draw line between old and new x, y coords
                g2.drawLine(oldX, oldY, currentX, currentY);
                repaint();

                //update old and new x, y coords
                oldX = currentX;
                oldY = currentY;

            }
        });
    }//end of canvas()

    protected void paintComponent(Graphics g) {
        if (canvasImage == null) {
            canvasImage = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) canvasImage.getGraphics();
        }

        g.drawImage(canvasImage, 0, 0, null);
    }

    public void saveImage(){
        Dimension imageSize = this.getSize();
        BufferedImage canvasExport = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = canvasExport.createGraphics();
        this.paint(g2d);

        try {
            ImageIO.write(canvasExport, "png", new File("exportedImage.png"));
            System.out.println("Wrote canvas to disk");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}//end of class




