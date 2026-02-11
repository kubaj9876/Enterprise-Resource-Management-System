import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class PracownicyDialog extends JDialog {
    private Storage storage;
    private Pracownik pracownik;
    private JTextField imieField, nazwiskoField, loginField, hasloField;
    private DateTimePicker dataUrodzeniaPicker;
    private JComboBox<DzialPracownikow> dzialCombo;
    private JCheckBox isUzytkownikCheck;

    public PracownicyDialog(Storage storage, Pracownik pracownik) {
        this.storage = storage;
        this.pracownik = pracownik;
        setTitle(pracownik == null ? "Dodaj pracownika" : "Edytuj pracownika");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Imię:"));
        imieField = new JTextField();
        panel.add(imieField);
        panel.add(new JLabel("Nazwisko:"));
        nazwiskoField = new JTextField();
        panel.add(nazwiskoField);
        panel.add(new JLabel("Data urodzenia:"));
        dataUrodzeniaPicker = new DateTimePicker();
        panel.add(dataUrodzeniaPicker);
        panel.add(new JLabel("Dział:"));
        dzialCombo = new JComboBox<>(storage.getDzialy().toArray(new DzialPracownikow[0]));
        panel.add(dzialCombo);
        panel.add(new JLabel("Użytkownik:"));
        isUzytkownikCheck = new JCheckBox();
        panel.add(isUzytkownikCheck);
        panel.add(new JLabel("Login:"));
        loginField = new JTextField();
        loginField.setEnabled(false);
        panel.add(loginField);
        panel.add(new JLabel("Hasło:"));
        hasloField = new JTextField();
        hasloField.setEnabled(false);
        panel.add(hasloField);

        isUzytkownikCheck.addActionListener(e -> {
            loginField.setEnabled(isUzytkownikCheck.isSelected());
            hasloField.setEnabled(isUzytkownikCheck.isSelected());
        });

        if (pracownik != null) {
            imieField.setText(pracownik.getImie());
            nazwiskoField.setText(pracownik.getNazwisko());
            dataUrodzeniaPicker.setSelectedDateTime(pracownik.getDataUrodzenia());
            dzialCombo.setSelectedItem(pracownik.getDzial());
            if (pracownik instanceof Uzytkownik) {
                isUzytkownikCheck.setSelected(true);
                loginField.setEnabled(true);
                hasloField.setEnabled(true);
                loginField.setText(((Uzytkownik) pracownik).getLogin());
                hasloField.setText(((Uzytkownik) pracownik).getHaslo());
            }
        }

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> saveEmployee());
        panel.add(new JLabel(""));
        panel.add(saveButton);
        add(panel);
    }

    private void saveEmployee() {
        try {
            String imie = imieField.getText();
            String nazwisko = nazwiskoField.getText();
            LocalDateTime dataUrodzenia = dataUrodzeniaPicker.getSelectedDateTime();
            DzialPracownikow dzial = (DzialPracownikow) dzialCombo.getSelectedItem();

            if (pracownik == null) {
                if (isUzytkownikCheck.isSelected()) {
                    String login = loginField.getText();
                    String haslo = hasloField.getText();
                    Uzytkownik uzytkownik = new Uzytkownik(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
                    storage.getPracownicy().add(uzytkownik);
                } else {
                    Pracownik newPracownik = new Pracownik(imie, nazwisko, dataUrodzenia, dzial);
                    storage.getPracownicy().add(newPracownik);
                }
            } else {
                pracownik.setImie(imie);
                pracownik.setNazwisko(nazwisko);
                pracownik.setDataUrodzenia(dataUrodzenia);
                pracownik.setDzial(dzial);
                if (pracownik instanceof Uzytkownik && isUzytkownikCheck.isSelected()) {
                    ((Uzytkownik) pracownik).setLogin(loginField.getText());
                    ((Uzytkownik) pracownik).setHaslo(hasloField.getText());
                }
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania: " + e.getMessage());
        }
    }
}