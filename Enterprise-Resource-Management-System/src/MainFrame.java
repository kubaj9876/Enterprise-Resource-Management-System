import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainFrame extends JFrame {
    public MainFrame(Storage storage, Uzytkownik loggedUser){
        setTitle("Aplikacja");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w){
                super.windowClosing(w);
                try {
                    storage.save();
                    System.out.println("Dane zapisane");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Działy", new DzialPanel(storage));
        tabbedPane.addTab("Pracownicy", new PracownicyPanel(storage, loggedUser));
        tabbedPane.addTab("Brygady", new BrygadaPanel(storage));
        tabbedPane.addTab("Zlecenia", new ZleceniePanel(storage));
        if (loggedUser instanceof Brygadzista) {
            tabbedPane.addTab("Moje zlecenia", new ZleceniaPanel(storage, (Brygadzista) loggedUser));
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        }

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Plik");
        JMenuItem logoutItem = new JMenuItem("Wyloguj");
        logoutItem.addActionListener(e -> {
            new LoginFrame(storage);
            dispose();
        });

        JMenuItem zmienHaslo = new JMenuItem("Zmien haslo");
        zmienHaslo.addActionListener(e -> {
            if (loggedUser instanceof Uzytkownik) {
                zmienHasloDialog dialog = new zmienHasloDialog((Uzytkownik) loggedUser);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Tylko użytkownicy mogą zmieniać hasło");
            }
        });
        fileMenu.add(logoutItem);
        fileMenu.add(zmienHaslo);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        add(tabbedPane);

    }
}
