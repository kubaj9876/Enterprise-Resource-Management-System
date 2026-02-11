import javax.swing.*;
import java.io.IOException;

public class S34308 {
    public static void main(String[] args) {
        Storage storage = new Storage();
        try{
            TD.test();
            storage.load();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginFrame(storage));
    }
}
