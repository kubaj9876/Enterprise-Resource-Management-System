import javax.swing.*;
import java.awt.*;

public class DzialDialog extends JDialog {
    private Storage storage;
    private DzialPracownikow dzial;
    private JTextField nazwaField;

    public DzialDialog(Storage storage, DzialPracownikow dzial) {
        this.storage = storage;
        this.dzial = dzial;
        setTitle(dzial == null ? "Dodaj dział" : "Edytuj dział");
        setModal(true);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nazwa:"));
        nazwaField = new JTextField();
        panel.add(nazwaField);

        if (dzial != null) {
            nazwaField.setText(dzial.getNazwa());
        }

        panel.add(new JLabel(""));
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> saveDzial());
        panel.add(saveButton);

        add(panel);
    }

    private void saveDzial() {
        String nazwa = nazwaField.getText();
        if (nazwa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nazwa działu nie może być pusta");
            return;
        }

        try {
            if (dzial == null) {
                if (!DzialPracownikow.isUnique(storage, nazwa)) {
                    JOptionPane.showMessageDialog(this, "Dział o tej nazwie już istnieje");
                    return;
                }
                DzialPracownikow newDzial = new DzialPracownikow(storage, nazwa);
                storage.getDzialy().add(newDzial);
            } else {
                if (!nazwa.equals(dzial.getNazwa()) && !DzialPracownikow.isUnique(storage, nazwa)) {
                    JOptionPane.showMessageDialog(this, "Dział o tej nazwie już istnieje");
                    return;
                }
                dzial.setNazwa(nazwa);
            }
            dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Błąd: " + e.getMessage());
        }
    }
}
