/**
 * Created by alex on 19/02/2019.
 */
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UILayout {

    //Swing Vars
    private JFrame mainWindow;
    private JPanel canvas;
    private JLabel digitOutput;
    private JButton detectButton;
    private JLabel text;

    //Paint Vars
    private int prevX;
    private int prevY;

    private boolean isDrawing;

    private Graphics graphicsDrawing;

    public UILayout() {

        //creating main window
        mainWindow = new JFrame("Digit Identifier");
        mainWindow.setLayout(new BorderLayout());

        //create content panel
        JPanel contentPanel = new JPanel();

        //creating components
        detectButton = new JButton ("Detect");
        canvas = new JPanel();


        //adding content panel to the main window
        mainWindow.add(contentPanel, BorderLayout.SOUTH);

        //setting up the canvas
        mainWindow.add(canvas, BorderLayout.CENTER);
        canvas.setBackground(Color.white);
        canvas.setSize(new Dimension(300, 300));

        //adding components to content panel
        contentPanel.add(detectButton);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

}
