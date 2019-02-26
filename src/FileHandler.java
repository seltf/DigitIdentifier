import java.io.File;
import java.io.FileInputStream;

/**
 * Author: Alexander Hill
 *
 *
 */
public class FileHandler {

    //
    private String train_label_filename = "";
    private String train_image_filename = "";

    private FileInputStream in_stream_labels = null;
    private FileInputStream in_stream_images = null;

    public FileHandler() {

        try {
            in_stream_labels = new FileInputStream(new File(train_label_filename));
            in_stream_images = new FileInputStream(new File(train_image_filename));
        } catch (Exception e) {
            System.out.println("An error ocurred while adding labels and images to the filestream");
        }


    }


}
