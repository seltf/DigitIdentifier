/**
 * Author: Alexander Hill
 *
 * This class handles the main layout of the program.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UILayout {

    //vars
    private JFrame mainWindow;
    private JPanel contentPanel, headerPanel;
    private JLabel digitOutput, outputLabel, canvasHeader;
    private JButton detectButton, clearCanvasButton;
    public String outputText = "Output: n/a";
    public int outputDigit;



    public UILayout() {

        //read the mnist data
        MnistLoader mnistLoader = new MnistLoader();
        //add the mnist data to arrays
        mnistLoader.loadData();

        //TODO: Delete this too.
        mnistLoader.debugExportMnistImage();

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
                    //save the current canvas state
                    canvas.saveImage();

                    //initiate the image processor class
                    new ImageProcessor();

                    //TODO: Change this to the predicted digit.
                    outputDigit = 5;

                    //update the output text with the predicted digit
                    updateOutput();
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
                    //reset the canvas to full black
                    canvas.clearCanvas();
                } catch (Exception e2) {
                    System.out.println("An error occurred while clearing the canvas");
                    e2.printStackTrace();
                }
            }
        }); //clear canvas button

        //setting the output text label
        outputLabel = new JLabel(" Output: ");

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
        contentPanel.add(outputLabel, BorderLayout.EAST);

        //final main window steps
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(250, 250);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);

    }//uilayout()

    //call this to update the output text
    public void updateOutput() {
        outputLabel.setText("Output: " + outputDigit);
    }

}//end of class
