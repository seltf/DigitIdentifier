/**
 * Created by alex on 20/02/2019.
 */

import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasHandler extends JPanel {

    //drawing vars
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;

    public CanvasHandler() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //mouse listeners
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                setStartPoint(e.getX(), e.getY());
            }

        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e){
                setEndPoint(e.getX(), e.getY());
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                setEndPoint(e.getX(), endY);
                repaint();
            }
        });

    }

    //canvas size
    public Dimension getPreferedSize() {
        return new Dimension(200, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("draw here!", 10, 20);
        g.setColor(Color.BLUE);
        drawSquare(g, this.startX, this.startY, this.endX, this.endY);
    }

    //drawing a rectangle
    private void setStartPoint(int currentX, int currentY) {
        this.startX = currentX;
        this.startY = currentY;
    }

    private void setEndPoint(int currentX, int currentY) {
        this.endX = currentX;
        this.endY = currentY;
    }

    public void mousePressed(MouseEvent e) {
        setStartPoint(e.getX(), e.getY());
    }

    private void drawSquare(Graphics g, int startX, int startY
            , int endX, int endY) {

        int px = Math.min(startX, endX);
        int py = Math.min(startY, endY);
        int pw = Math.abs(startX - endX);
        int ph = Math.abs(startY - endY);
        g.drawRect(px, py, pw, ph);

    }

}