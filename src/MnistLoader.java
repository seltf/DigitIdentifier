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
    private String train_label_filename = "/Users/alex/Documents/mnist/train-labels.idx1-ubyte";
    private String train_image_filename = "/Users/alex/Documents/mnist/train-images.idx3-ubyte";

    private FileInputStream in_stream_labels = null;
    private FileInputStream in_stream_images = null;

    public MnistLoader() {

        try {
            in_stream_labels = new FileInputStream(new File(train_label_filename));
            in_stream_images = new FileInputStream(new File(train_image_filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numberOfLabels = 0;
        try { //reading the data
            int labels_start_code = (in_stream_labels.read() << 24) |
                    (in_stream_labels.read() << 16) |
                    (in_stream_labels.read() << 8) |
                    (in_stream_labels.read());

            //print label start code
            System.out.println("label start code: " + labels_start_code);

            int images_start_code = (in_stream_images.read() << 24) |
                    (in_stream_images.read() << 16) |
                    (in_stream_images.read() << 8) |
                    (in_stream_images.read());

            //print images start code
            System.out.println("images start code: " + images_start_code);

            //read next 4 bytes for number of labels
            numberOfLabels = (in_stream_labels.read() << 24) |
                    (in_stream_labels.read() << 16) |
                    (in_stream_labels.read() << 8) |
                    (in_stream_labels.read());

            //read next 4 bytes for number of images
            int numberOfImages = (in_stream_images.read() << 24) |
                    (in_stream_images.read() << 16) |
                    (in_stream_images.read() << 8) |
                    (in_stream_images.read());

            //print number of labels and images
            System.out.println("number of labels and images: " + numberOfLabels + " and " + numberOfImages);

            //read next 4 bytes for rows
            int imageHeight = (in_stream_images.read() << 24) |
                    (in_stream_images.read() << 16) |
                    (in_stream_images.read() << 8) |
                    (in_stream_images.read());

            //read next 4 bytes for columns
            int imageWidth = (in_stream_images.read() << 24) |
                    (in_stream_images.read() << 16) |
                    (in_stream_images.read() << 8) |
                    (in_stream_images.read());

            System.out.println("image size: " + imageWidth + " x " + imageHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }//end of reading data

        int[] labelList = new int[numberOfLabels];

    }//end of mnistloader()

}//end of class
