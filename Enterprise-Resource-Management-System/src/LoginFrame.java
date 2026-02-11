import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame(Storage storage){
        setTitle("Logowanie");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Login:"));
        JTextField loginField = new JTextField();
        panel.add(loginField);
        panel.add(new JLabel("Hasło:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);
        panel.add(new JLabel());
        JButton loginButton = new JButton("Zaloguj");
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            for(Pracownik u : storage.getPracownicy()){
                if(u instanceof Uzytkownik || u instanceof Brygadzista){
                    Uzytkownik uzytkownik = (Uzytkownik) u;
                    if(uzytkownik.getLogin().equals(login) && uzytkownik.getHaslo().equals(password)){
                        new MainFrame(storage, uzytkownik);
                        dispose();
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Zły login lub hasło");
        });

        add(panel);
    }
}
