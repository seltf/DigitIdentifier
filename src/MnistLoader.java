import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: Alexander Hill
 *
 * Read MNIST data and store it in an array
 */
public class MnistLoader {

    //
    private String trainLabelFilename = "train-labels.idx1-ubyte";
    private String trainImageFilename = "train-images.idx3-ubyte";

    private FileInputStream inStreamLabels = null;
    private FileInputStream inStreamImages = null;

    int numberOfLabels = 0;
    int numberOfImages = 0;
    int imageHeight = 0;
    int imageWidth = 0;
    int imageSize = 0;

    public ArrayList<BufferedImage> mnistArrayList = new ArrayList<BufferedImage>();

    public MnistLoader() {
        try {
            inStreamLabels = new FileInputStream(new File(trainLabelFilename));
            inStreamImages = new FileInputStream(new File(trainImageFilename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { //reading the data
            int labels_start_code = (inStreamLabels.read() << 24) |
                    (inStreamLabels.read() << 16) |
                    (inStreamLabels.read() << 8) |
                    (inStreamLabels.read());

            //print label start code
            System.out.println("label start code: " + labels_start_code);

            int images_start_code = (inStreamImages.read() << 24) |
                    (inStreamImages.read() << 16) |
                    (inStreamImages.read() << 8) |
                    (inStreamImages.read());

            //print images start code
            System.out.println("images start code: " + images_start_code);

            //read next 4 bytes for number of labels
            numberOfLabels = (inStreamLabels.read() << 24) |
                    (inStreamLabels.read() << 16) |
                    (inStreamLabels.read() << 8) |
                    (inStreamLabels.read());

            //read next 4 bytes for number of images
            numberOfImages = (inStreamImages.read() << 24) |
                    (inStreamImages.read() << 16) |
                    (inStreamImages.read() << 8) |
                    (inStreamImages.read());

            //print number of labels and images
            System.out.println("number of labels and images: " + numberOfLabels + " and " + numberOfImages);

            //read next 4 bytes for rows
            imageHeight = (inStreamImages.read() << 24) |
                    (inStreamImages.read() << 16) |
                    (inStreamImages.read() << 8) |
                    (inStreamImages.read());

            //read next 4 bytes for columns
            imageWidth = (inStreamImages.read() << 24) |
                    (inStreamImages.read() << 16) |
                    (inStreamImages.read() << 8) |
                    (inStreamImages.read());

            System.out.println("image size: " + imageWidth + " x " + imageHeight);
            imageSize = imageWidth * imageHeight;



        } catch (IOException e) {
            e.printStackTrace();
        }//end of reading data

    }//end of mnistloader()

    public void loadData(){
        //create two new list for images and labels
        int[] labelList = new int[numberOfLabels];
        int[] imageData = new int[numberOfImages];

        //new buffered image for loading the images
        BufferedImage currentImage;

        //loop as many times as there are images
        System.out.println("Loading...");
        for (int i = 0; i < numberOfImages; i++) {
            //the current image = the h and w of the data read from the mnist file
            currentImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_ARGB);

            //read the next bytes from the label input
            try{
                int label = inStreamLabels.read();
                labelList[i] = label;
                //System.out.println("Reading labels to list: " + "(" + i + "/" + numberOfLabels + ")");
            } catch (Exception e) {
                System.out.println("error loading images");
                e.printStackTrace();
            }

            //loop as many times as there are pixels in each image
            for (int px = 0; px < imageSize; px++){
                //read the next bytes in the stream
                try {
                    int grayValue = inStreamImages.read();
                    int rgbValue = 0xFF000000 | (grayValue << 16) |
                            (grayValue << 8) |
                            (grayValue);

                    imageData[px] = rgbValue;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }//end of loop (pixels)

            //set pixel array to the current image
            currentImage.setRGB(0, 0, imageWidth, imageHeight, imageData, 0, imageWidth);

            try {
                //try to add the current image to an arraylist
                mnistArrayList.add(currentImage);
            } catch (Exception e) {
                System.out.println("Error when adding image to arraylist");
                e.printStackTrace();
            }

        }//end of loop (images)

        System.out.println("Loading complete");
    }//end of loadData()

    //TODO: Delete this.
    public void debugExportMnistImage(){

        Graphics2D g2d = mnistArrayList.get(560).createGraphics();
        this.paint(g2d);
 
        try {
            //write the buffered image to disk as png
            ImageIO.write(mnistArrayList.get(560), "png", new File("mnistTestImage.png"));
            System.out.println("Wrote mnist to disk");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void paint(Graphics2D g2d) {
    }


}//end of class
