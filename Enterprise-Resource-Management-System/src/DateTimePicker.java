import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class DateTimePicker extends JPanel {
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;

    public DateTimePicker() {
        setLayout(new FlowLayout());
        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        yearSpinner = new JSpinner(new SpinnerNumberModel(2023, 1900, 2100, 1));
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        add(new JLabel("D:"));
        add(daySpinner);
        add(new JLabel("M:"));
        add(monthSpinner);
        add(new JLabel("R:"));
        add(yearSpinner);
        add(new JLabel("G:"));
        add(hourSpinner);
        add(new JLabel("M:"));
        add(minuteSpinner);
    }

    public LocalDateTime getSelectedDateTime() {
        return LocalDateTime.of(
                (int) yearSpinner.getValue(),
                (int) monthSpinner.getValue(),
                (int) daySpinner.getValue(),
                (int) hourSpinner.getValue(),
                (int) minuteSpinner.getValue()
        );
    }

    public void setSelectedDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            daySpinner.setValue(dateTime.getDayOfMonth());
            monthSpinner.setValue(dateTime.getMonthValue());
            yearSpinner.setValue(dateTime.getYear());
            hourSpinner.setValue(dateTime.getHour());
            minuteSpinner.setValue(dateTime.getMinute());
        }
    }
}