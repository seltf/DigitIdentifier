import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Author: Alexander Hill
 *
 * Read MNIST data and store it in an array
 */
public class MnistLoader {

    //
    private String trainLabelFilename = "/Users/alex/Documents/mnist/train-labels.idx1-ubyte";
    private String trainImageFilename = "/Users/alex/Documents/mnist/train-images.idx3-ubyte";

    private FileInputStream inStreamLabels = null;
    private FileInputStream inStreamImages = null;

    public MnistLoader() {

        try {
            inStreamLabels = new FileInputStream(new File(trainLabelFilename));
            inStreamImages = new FileInputStream(new File(trainImageFilename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numberOfLabels = 0;
        int numberOfImages = 0;
        int imageHeight = 0;
        int imageWidth = 0;
        int imageSize = 0;

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

        //create two new list for images and labels
        int[] labelList = new int[numberOfLabels];
        int[] imageData = new int[numberOfImages];

        //new buffered image for loading the images
        BufferedImage currentImage;

        //loop as many times as there are images
        for (int i = 0; i < numberOfImages; i++) {
            //the current image = the h and w of the data read from the mnist file
            currentImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_ARGB);

            //read the next bytes from the label input
            try{
                int label = inStreamLabels.read();
                labelList[i] = label;
                System.out.println("Reading labels to list: " + "(" + i + "/" + numberOfLabels + ")");
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

            currentImage.setRGB(0, 0, imageWidth, imageHeight, imageData, 0, imageWidth);

        }//end of loop (images)

    }//end of mnistloader()

}//end of class
