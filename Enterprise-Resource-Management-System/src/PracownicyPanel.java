import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PracownicyPanel extends JPanel {
    private Storage storage;
    private JTable table;
    private DefaultTableModel tableModel;
    private Uzytkownik loggedUser;

    public PracownicyPanel(Storage storage, Uzytkownik loggedUser){
        this.storage = storage;
        this.loggedUser = loggedUser;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Imie", "Nazwisko", "Data urodzenia", "Dzial"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadEmployees();

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        JButton editButton = new JButton("Edytuj");
        JButton deleteButton = new JButton("Usun");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployee());
        editButton.addActionListener(e -> editEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);
        for (Pracownik p : storage.getPracownicy()) {
            tableModel.addRow(new Object[]{p.getImie(), p.getNazwisko(), p.getDataUrodzenia(), p.getDzial().getNazwa()});
        }
    }

    private void addEmployee() {
        PracownicyDialog dialog = new PracownicyDialog(storage, null);
        dialog.setVisible(true);
        loadEmployees();
    }

    private void editEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            java.util.List<Pracownik> pracownicy = storage.getPracownicy();
            if (selectedRow < pracownicy.size()) {
                Pracownik selected = pracownicy.get(selectedRow);
                if (selected.equals(loggedUser)) {
                    JOptionPane.showMessageDialog(this, "Nie możesz edytować własnych danych");
                    return;
                }
                PracownicyDialog dialog = new PracownicyDialog(storage, selected);
                dialog.setVisible(true);
                loadEmployees();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz pracownika do edycji");
        }
    }

    private void deleteEmployee() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć wybranych pracowników?", "Potwierdź usunięcie", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                List<Pracownik> pracownicy = storage.getPracownicy();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    if (row < pracownicy.size()) {
                        if (pracownicy.get(row).equals(loggedUser)) {
                            JOptionPane.showMessageDialog(this, "Nie możesz usunąć własnego konta");
                            continue;
                        }
                        pracownicy.remove(row);
                    }
                }
                loadEmployees();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz pracowników do usunięcia");
        }
    }
}
