import javax.swing.*;
import java.awt.*;

public class CanvasHandler extends JPanel {

    public CanvasHandler() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public Dimension getPreferedSize() {

        return new Dimension(200, 200);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawString("draw here!", 10, 20);

    }


}