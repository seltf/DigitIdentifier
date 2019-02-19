/**
 * Created by alex on 19/02/2019.
 */
public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    new UILayout();

                } catch (Exception e) {
                    System.out.println("Error occured while initiating "
                            + "the swing thread.");
                }

            }

        });

    }

}