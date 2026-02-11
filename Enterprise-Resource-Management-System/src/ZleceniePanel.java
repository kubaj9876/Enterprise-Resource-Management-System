import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ZleceniePanel extends JPanel {
    private Storage storage;
    private JTable table;
    private DefaultTableModel tableModel;

    public ZleceniePanel(Storage storage) {
        this.storage = storage;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Brygada", "Stan", "Data Utworzenia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        showZleceniePrace(row);
                    }
                }
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadZlecenia();

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        JButton editButton = new JButton("Edytuj");
        JButton deleteButton = new JButton("Usuń");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addZlecenie());
        editButton.addActionListener(e -> editZlecenie());
        deleteButton.addActionListener(e -> deleteZlecenie());
    }

    private void loadZlecenia() {
        tableModel.setRowCount(0);
        for (Zlecenie z : storage.getZlecenia()) {
            tableModel.addRow(new Object[]{z.getBrygada().getNazwa(), z.getStan(), z.getDataUtworzenia()});
        }
    }

    private void addZlecenie() {
        ZlecenieDialog dialog = new ZlecenieDialog(storage, null);
        dialog.setVisible(true);
        loadZlecenia();
    }

    private void editZlecenie() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            java.util.List<Zlecenie> zlecenia = storage.getZlecenia();
            if (selectedRow < zlecenia.size()) {
                Zlecenie selected = zlecenia.get(selectedRow);
                ZlecenieDialog dialog = new ZlecenieDialog(storage, selected);
                dialog.setVisible(true);
                loadZlecenia();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zlecenie do edycji");
        }
    }

    private void deleteZlecenie() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć wybrane zlecenia?", "Potwierdź usunięcie", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                java.util.List<Zlecenie> zlecenia = storage.getZlecenia();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    if (row < zlecenia.size()) {
                        zlecenia.remove(row);
                    }
                }
                loadZlecenia();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zlecenia do usunięcia");
        }
    }

    private void showZleceniePrace(int row) {
        List<Zlecenie> zlecenia = storage.getZlecenia();
        if (row < zlecenia.size()) {
            Zlecenie selected = zlecenia.get(row);
            StringBuilder prace = new StringBuilder("Prace w zleceniu:\n");
            for (Praca p : selected.getListaPrac()) {
                prace.append("Numer: ").append(p.getId())
                        .append(", Rodzaj: ").append(p.getRodzaj())
                        .append(", Czas: ").append(p.getCzasPracy())
                        .append(", Zrealizowane: ").append(p.czyZrealizowane() ? "Tak" : "Nie")
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, prace.toString(), "Prace w zleceniu", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
