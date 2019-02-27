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
    private JPanel contentPanel, headerPanel;
    private JLabel digitOutput, outputText, canvasHeader;
    private JButton detectButton, clearCanvasButton;

    public UILayout() {

        Canvas canvas = new Canvas();
        //creating main window
        mainWindow = new JFrame("Digit Identifier");
        mainWindow.setLayout(new BorderLayout());

        //create content panel
        contentPanel = new JPanel();

        //create header panel
        headerPanel = new JPanel();

        //setting up detect button
        detectButton = new JButton ("Detect");
        detectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Detect button triggered");
                try {
                    canvas.saveImage();
                } catch (Exception e2) {
                    System.out.println("An error occurred while writing image to disk");
                }

            }//action listener
        });//detect button

        //setting up the clear canvas button
        clearCanvasButton = new JButton("Clear");
        clearCanvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clear canvas button triggered");
                try {
                    canvas.clearCanvas();
                } catch (Exception e2) {
                    System.out.println("An error occurred while clearing the canvas");
                    e2.printStackTrace();
                }
            }
        }); //clear canvas button

        //setting the output text label
        outputText = new JLabel(" Output: ");

        //setting the canvas header text label
        canvasHeader = new JLabel("Draw in the space below: ");

        //adding content panel to the main window
        mainWindow.add(contentPanel, BorderLayout.SOUTH);

        //adding the canvas and catching errors
        try {
            mainWindow.add(canvas, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("An error occurred while initiating the canvas.");
        }

        //adding components to content panel
        contentPanel.add(detectButton);
        contentPanel.add(clearCanvasButton);
        contentPanel.add(outputText, BorderLayout.EAST);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(250, 250);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);

        new FileHandler();

    }//uilayout()

}//end of class
