package tasktracker;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public JTextField titleField = new JTextField();
    public JComboBox<String> priorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
    public JButton addButton = new JButton("Add Task");
    public JButton toggleButton = new JButton("Toggle Status");
    public JButton deleteButton = new JButton("Delete Task");

    public InputPanel() {
        setLayout(new GridLayout(4, 2, 8, 8));
        setBorder(BorderFactory.createTitledBorder("Manage Tasks"));

        add(new JLabel("Task Title:"));
        add(titleField);
        add(new JLabel("Priority:"));
        add(priorityBox);
        add(addButton);
        add(toggleButton);
        add(deleteButton);
    }
}