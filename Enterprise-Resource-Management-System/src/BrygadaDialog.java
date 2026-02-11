import javax.swing.*;
import java.awt.*;

public class BrygadaDialog extends JDialog {
    private Storage storage;
    private Brygada brygada;
    private JTextField nazwaField;
    private JComboBox<Brygadzista> brygadzistaCombo;

    public BrygadaDialog(Storage storage, Brygada brygada) {
        this.storage = storage;
        this.brygada = brygada;
        setTitle(brygada == null ? "Dodaj brygadę" : "Edytuj brygadę");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nazwa:"));
        nazwaField = new JTextField();
        panel.add(nazwaField);
        panel.add(new JLabel("Brygadzista:"));
        brygadzistaCombo = new JComboBox<>(storage.getPracownicy().stream()
                .filter(p -> p instanceof Brygadzista)
                .toArray(Brygadzista[]::new));
        panel.add(brygadzistaCombo);

        if (brygada != null) {
            nazwaField.setText(brygada.getNazwa());
            brygadzistaCombo.setSelectedItem(brygada.getBrygadzista());
        }

        panel.add(new JLabel(""));
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> saveBrygada());
        panel.add(saveButton);
        add(panel);
    }

    private void saveBrygada() {
        try {
            String nazwa = nazwaField.getText();
            Brygadzista brygadzista = (Brygadzista) brygadzistaCombo.getSelectedItem();
            if (brygada == null) {
                Brygada newBrygada = new Brygada(nazwa, brygadzista);
                storage.getBrygady().add(newBrygada);
                brygadzista.dodajBrygade(newBrygada);
            } else {
                brygada.setNazwa(nazwa);
                brygada.setBrygadzista(brygadzista);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania: " + e.getMessage());
        }
    }
}
