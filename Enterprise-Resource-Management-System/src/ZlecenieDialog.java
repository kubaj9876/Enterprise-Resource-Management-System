import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class ZlecenieDialog extends JDialog {
    private Storage storage;
    private Zlecenie zlecenie;
    private JComboBox<Brygada> brygadaCombo;
    private JCheckBox planowaneCheck;
    private DateTimePicker dataRealizacjiPicker;

    public ZlecenieDialog(Storage storage, Zlecenie zlecenie) {
        this.storage = storage;
        this.zlecenie = zlecenie;
        setTitle(zlecenie == null ? "Dodaj zlecenie" : "Edytuj zlecenie");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Brygada:"));
        brygadaCombo = new JComboBox<>(storage.getBrygady().toArray(new Brygada[0]));
        panel.add(brygadaCombo);
        panel.add(new JLabel("Planowane:"));
        planowaneCheck = new JCheckBox();
        panel.add(planowaneCheck);
        panel.add(new JLabel("Data realizacji:"));
        dataRealizacjiPicker = new DateTimePicker();
        panel.add(dataRealizacjiPicker);

        if (zlecenie != null) {
            brygadaCombo.setSelectedItem(zlecenie.getBrygada());
            planowaneCheck.setSelected(zlecenie.getStan() == stanZlecenia.Planowane);
            dataRealizacjiPicker.setSelectedDateTime(zlecenie.getDataRealizacji());
        }

        panel.add(new JLabel(""));
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> saveZlecenie());
        panel.add(saveButton);
        add(panel);
    }

    private void saveZlecenie() {
        try {
            Brygada brygada = (Brygada) brygadaCombo.getSelectedItem();
            boolean planowane = planowaneCheck.isSelected();
            LocalDateTime dataRealizacji = dataRealizacjiPicker.getSelectedDateTime();
            if (zlecenie == null) {
                Zlecenie newZlecenie = new Zlecenie(brygada, planowane);
                newZlecenie.setDataRealizacji(dataRealizacji);
                storage.getZlecenia().add(newZlecenie);
            } else {
                zlecenie.setBrygada(brygada);
                zlecenie.setStan(planowane ? stanZlecenia.Planowane : stanZlecenia.Nieplanowane);
                zlecenie.setDataRealizacji(dataRealizacji);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania: " + e.getMessage());
        }
    }
}
