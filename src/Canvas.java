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
import java.nio.Buffer;

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
                g2.setPaint(Color.black);
                g2.fillRect(0,0,getSize().width, getSize().height);
                g2.setPaint(Color.white);
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
    public void clearCanvas() {
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.WHITE);
        repaint();
    }
    public void saveImage(){
        //create a new buffered image the size of the canvas
        //Dimension imageSize = this.getSize();
        //BufferedImage canvasExport = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_BYTE_GRAY);

        BufferedImage resizedImage = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(canvasImage, 0, 0, 28, 28, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        this.paint(g);
        //create a graphics2d object of the canvas and paint to it
        //Graphics2D g2d = canvasExport.createGraphics();
        //this.paint(g2d);

        try {
            //write the buffered image to disk as png
            ImageIO.write(resizedImage, "png", new File("exportedImage.png"));
            System.out.println("Wrote canvas to disk");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//end of class




