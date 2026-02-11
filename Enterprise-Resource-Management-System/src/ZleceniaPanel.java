import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ZleceniaPanel extends JPanel {
    private Storage storage;
    private Brygadzista brygadzista;
    private JTable table;
    private DefaultTableModel tableModel;

    public ZleceniaPanel(Storage storage, Brygadzista brygadzista) {
        this.storage = storage;
        this.brygadzista = brygadzista;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Brygada", "Stan", "Data Utworzenia"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadMyZlecenia();

        JPanel buttonPanel = new JPanel();
        JButton completeButton = new JButton("Zakończ zlecenie");
        buttonPanel.add(completeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        completeButton.addActionListener(e -> completeZlecenie());
    }

    private void loadMyZlecenia() {
        tableModel.setRowCount(0);
        List<Zlecenie> mojeZlecenia = storage.getZlecenia().stream()
                .filter(z -> {
                    Brygadzista zBrygadzista = z.getBrygada().getBrygadzista();
                    return zBrygadzista != null && zBrygadzista.equals(brygadzista) && z.getStan() != stanZlecenia.Zakonczone;
                })
                .toList();
        for (Zlecenie z : mojeZlecenia) {
            tableModel.addRow(new Object[]{z.getBrygada().getNazwa(), z.getStan(), z.getDataUtworzenia()});
        }
    }

    private void completeZlecenie() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Zlecenie> mojeZlecenia = storage.getZlecenia().stream()
                    .filter(z -> z.getBrygada().getBrygadzista().equals(brygadzista) && z.getStan() != stanZlecenia.Zakonczone)
                    .toList();
            Zlecenie selected = mojeZlecenia.get(selectedRow);
            selected.zakonczZlecenie();
            loadMyZlecenia();
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zlecenie do zakończenia");
        }
    }
}
