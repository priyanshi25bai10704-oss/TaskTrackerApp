package tasktracker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main extends JFrame {
    private TaskManager manager = new TaskManager();
    private InputPanel inputPanel = new InputPanel();
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JLabel summaryLabel = new JLabel("Completed: 0 / Total: 0");

    public Main() {
        setTitle("Personal Task & Habit Tracker");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Task Title", "Priority", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        taskTable = new JTable(tableModel);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryPanel.add(summaryLabel);
        add(summaryPanel, BorderLayout.SOUTH);

        inputPanel.addButton.addActionListener(e -> addTask());
        inputPanel.toggleButton.addActionListener(e -> toggleTaskStatus());
        inputPanel.deleteButton.addActionListener(e -> deleteTask());
    }

    private void addTask() {
        String title = inputPanel.titleField.getText().trim();
        String priority = (String) inputPanel.priorityBox.getSelectedItem();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task title cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Task task = new Task(title, priority, "Pending");
        manager.addTask(task);
        tableModel.addRow(new Object[]{title, priority, "Pending"});
        updateSummary();

        FileStorage.saveTasks(manager.getTasks());
        inputPanel.titleField.setText("");
    }

    private void toggleTaskStatus() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow != -1) {
            manager.toggleStatus(selectedRow);
            Task updatedTask = manager.getTasks().get(selectedRow);
            tableModel.setValueAt(updatedTask.getStatus(), selectedRow, 2);
            updateSummary();
            FileStorage.saveTasks(manager.getTasks());
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to toggle status.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow != -1) {
            manager.removeTask(selectedRow);
            tableModel.removeRow(selectedRow);
            updateSummary();
            FileStorage.saveTasks(manager.getTasks());
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateSummary() {
        int completed = manager.getCompletedCount();
        int total = manager.getTasks().size();
        summaryLabel.setText(String.format("Completed: %d / Total: %d", completed, total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}