import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DzialPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private Storage storage;

    public DzialPanel(Storage storage){
        this.storage = storage;

        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Nazwa"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        showDzialPracownicy(row);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        JButton editButton = new JButton("Edytuj");
        JButton deleteButton = new JButton("Usuń");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            addDzial();
        });

        editButton.addActionListener(e -> {
            editDzial();
        });

        deleteButton.addActionListener(e -> {
            deleteDzial();
        });

        loadDzialy();
    }

    private void showDzialPracownicy(int row) {
        List<DzialPracownikow> dzialy = storage.getDzialy();
        if (row < dzialy.size()) {
            DzialPracownikow selected = dzialy.get(row);
            List<Pracownik> pracownicyWDziale = storage.getPracownicy().stream()
                    .filter(p -> p.getDzial().equals(selected))
                    .toList();

            StringBuilder pracownicyInfo = new StringBuilder("Pracownicy w dziale " + selected.getNazwa() + ":\n\n");
            if (pracownicyWDziale.isEmpty()) {
                pracownicyInfo.append("Brak pracowników w tym dziale.");
            } else {
                for (Pracownik p : pracownicyWDziale) {
                    pracownicyInfo.append("Imię: ").append(p.getImie())
                            .append("\nNazwisko: ").append(p.getNazwisko())
                            .append("\nData urodzenia: ").append(p.getDataUrodzenia())
                            .append("\nDział: ").append(p.getDzial().getNazwa())
                            .append("\n------------------------\n");
                }
            }

            JTextArea textArea = new JTextArea(pracownicyInfo.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Pracownicy w dziale", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadDzialy() {
        tableModel.setRowCount(0);
        for (DzialPracownikow d : storage.getDzialy()) {
            tableModel.addRow(new Object[]{d.getNazwa()});
        }
    }

    private void addDzial(){
        DzialDialog dialog = new DzialDialog(storage, null);
        dialog.setVisible(true);
        loadDzialy();
    }
    private void editDzial(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<DzialPracownikow> dzialy = storage.getDzialy();
            if (selectedRow < dzialy.size()) {
                DzialPracownikow selected = dzialy.get(selectedRow);
                DzialDialog dialog = new DzialDialog(storage, selected);
                dialog.setVisible(true);
                loadDzialy();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz dział do edycji");
        }
    }
    private void deleteDzial(){
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć wybrane działy?", "Potwierdź usunięcie", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                List<DzialPracownikow> dzialy = storage.getDzialy();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    if (row < dzialy.size()) {
                        boolean isUsed = storage.getPracownicy().stream()
                                .anyMatch(p -> p.getDzial().equals(dzialy.get(row)));
                        if (isUsed) {
                            JOptionPane.showMessageDialog(this, "Nie można usunąć działu " + dzialy.get(row).getNazwa() + ", ponieważ jest używany przez pracowników.");
                            continue;
                        }
                        dzialy.remove(row);
                    }
                }
                loadDzialy();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz działy do usunięcia");
        }
    }
}
