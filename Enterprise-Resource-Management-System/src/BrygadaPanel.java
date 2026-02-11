import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BrygadaPanel extends JPanel {
    private Storage storage;
    private JTable table;
    private DefaultTableModel tableModel;

    public BrygadaPanel(Storage storage) {
        this.storage = storage;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Nazwa", "Brygadzista"}, 0) {
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
                        showBrigadeMembers(row);
                    }
                }
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBrygady();

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        JButton editButton = new JButton("Edytuj");
        JButton deleteButton = new JButton("Usuń");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addBrygada());
        editButton.addActionListener(e -> editBrygada());
        deleteButton.addActionListener(e -> deleteBrygada());
    }

    private void loadBrygady() {
        tableModel.setRowCount(0);
        for (Brygada b : storage.getBrygady()) {
            String brygadzistaName = b.getBrygadzista().getImie() + " " + b.getBrygadzista().getNazwisko();
            tableModel.addRow(new Object[]{b.getNazwa(), brygadzistaName});
        }
    }

    private void addBrygada() {
        BrygadaDialog dialog = new BrygadaDialog(storage, null);
        dialog.setVisible(true);
        loadBrygady();
    }

    private void editBrygada() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            java.util.List<Brygada> brygady = storage.getBrygady();
            if (selectedRow < brygady.size()) {
                Brygada selected = brygady.get(selectedRow);
                BrygadaDialog dialog = new BrygadaDialog(storage, selected);
                dialog.setVisible(true);
                loadBrygady();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz brygadę do edycji");
        }
    }

    private void deleteBrygada() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć wybrane brygady?", "Potwierdź usunięcie", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                java.util.List<Brygada> brygady = storage.getBrygady();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    if (row < brygady.size()) {
                        brygady.remove(row);
                    }
                }
                loadBrygady();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz brygady do usunięcia");
        }
    }

    private void showBrigadeMembers(int row) {
        List<Brygada> brygady = storage.getBrygady();
        if (row < brygady.size()) {
            Brygada selected = brygady.get(row);
            StringBuilder members = new StringBuilder("Pracownicy w brygadzie " + selected.getNazwa() + ":\n");
            for (Pracownik p : selected.getListaPracownikow()) {
                members.append(p.getImie()).append(" ").append(p.getNazwisko()).append("\n");
            }
            JOptionPane.showMessageDialog(this, members.toString(), "Członkowie brygady", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}