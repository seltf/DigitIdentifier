/**
 * Author: Alexander Hill
 *
 * Initiate the program.
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

        }); //run

    }

}