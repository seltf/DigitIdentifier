/**
 * Created by alex on 19/02/2019.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UILayout {

    private JFrame mainWindow;
    private JPanel canvas;
    private JLabel digitOutput;
    private JButton detectButton;
    private JLabel text;

    public UILayout() {

        //creating main window
        mainWindow = new JFrame("Digit Identifier");
        mainWindow.setLayout(new BorderLayout());

        //create content panel
        JPanel contentPanel = new JPanel();

        //creating components
        detectButton = new JButton ("Detect");
        text = new JLabel ("test text");

        //adding content panel to the main window
        mainWindow.add(contentPanel, BorderLayout.SOUTH);

        //adding components to content panel
        contentPanel.add(detectButton);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(new Dimension(500, 500));
        mainWindow.setVisible(true);
    }

}
