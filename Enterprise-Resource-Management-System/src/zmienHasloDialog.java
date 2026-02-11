import javax.swing.*;
import java.awt.*;

public class zmienHasloDialog extends JDialog {
    private Uzytkownik loggedUser;
    private JTextField oldPasswordField;
    private JTextField newPasswordField;
    private JTextField confirmPasswordField;

    public zmienHasloDialog(Uzytkownik loggedUser) {
        this.loggedUser = loggedUser;
        setTitle("Zmień hasło");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Stare hasło:"));
        oldPasswordField = new JPasswordField();
        panel.add(oldPasswordField);
        panel.add(new JLabel("Nowe hasło:"));
        newPasswordField = new JPasswordField();
        panel.add(newPasswordField);
        panel.add(new JLabel("Potwierdź nowe hasło:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);
        panel.add(new JLabel(""));
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> savePassword());
        panel.add(saveButton);

        add(panel);
    }
    private void savePassword() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!oldPassword.equals(loggedUser.getHaslo())) {
            JOptionPane.showMessageDialog(this, "Stare hasło jest nieprawidłowe");
            return;
        }

        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nowe hasło nie może być puste");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Nowe hasła nie są takie same");
            return;
        }

        loggedUser.setHaslo(newPassword);
        JOptionPane.showMessageDialog(this, "Hasło zostało zmienione");
        dispose();
    }
}
