/**
 * Created by alex on 19/02/2019.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UILayout {

    //swing Vars
    private JFrame mainWindow;
    private JPanel canvasPanel;
    private JLabel digitOutput;
    private JButton detectButton;
    private JLabel text;

    public UILayout() {

        //creating main window
        mainWindow = new JFrame("Digit Identifier");
        mainWindow.setLayout(new BorderLayout());

        //create content panel
        JPanel contentPanel = new JPanel();

        //setting up detect button
        detectButton = new JButton ("Detect");
        detectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("event triggered.");
                Dimension imageSize = canvasPanel.getSize();

                try {
                    //save image function
                } catch (Exception e1) {
                    System.out.println("Error occurred while triggering event.");
                }
            }
        });

        canvasPanel = new JPanel();

        //adding content panel to the main window
        mainWindow.add(contentPanel, BorderLayout.SOUTH);

        //adding the canvas
        mainWindow.add(new CanvasHandler());

        //adding components to content panel
        contentPanel.add(detectButton);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 500);
        mainWindow.setVisible(true);

    }

    public void saveCanvasAsImage(){

        try {

        } catch (Exception e) {
            System.out.println("Error occurred while saving image.");
        }

    }

}
