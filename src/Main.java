/**
 * Created by alex on 19/02/2019.
 */
public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    //create window
                    new UILayout();

                } catch (Exception e) {
                    System.out.println("Error occurred while initiating "
                            + "the swing thread.");
                }

            }

        });

    }

}