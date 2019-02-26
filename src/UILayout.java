/**
 * Author: Alexander Hill
 *
 * This class handles the main layout of the program.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
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

        Canvas canvas = new Canvas();

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
                System.out.println("Detect button triggered");

                //convert canvas to a buffered image


                //write to disk
                try {

                    canvas.saveImage();

                } catch (Exception e2) {
                    System.out.println("An error occurred while saving image to disk");
                }

            }//action listener
        });//detect button

        //adding content panel to the main window
        mainWindow.add(contentPanel, BorderLayout.SOUTH);

        //adding the canvas
        try {
            mainWindow.add(canvas);
        } catch (Exception e) {
            System.out.println("An error occurred while initiating the canvas.");
        }

        //adding components to content panel
        contentPanel.add(detectButton);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 500);
        mainWindow.setVisible(true);

    }//uilayout()

}//end of class
