/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author N TECH
 */
package gui_version;

import console_version.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Queue;

public class PlayerDisplayFrame extends JFrame {

    private QueueManager playerManager;
    private JTable table;
    private DefaultTableModel model;

    public PlayerDisplayFrame(QueueManager manager) {
        this.playerManager = manager;

        setTitle("Player Statistics");
        setSize(950, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Table Columns
        String[] columns = {
            "ID", "Name", "Role", "Runs", "Wickets", "Matches",
            "Average", "Strike Rate", "Economy", "Team"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // non-editable table
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(0, 173, 181));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load data first time
        refresh();
        setVisible(true);
    }

    // Refresh Table Data
    public void refresh() {
        model.setRowCount(0); // clear table

        if (playerManager == null || playerManager.getQueue() == null) {
            System.out.println("No data to refresh.");
            return;
        }

        Queue<Player> queue = playerManager.getQueue();
        for (Player p : queue) {
            String wickets = p.getRole().equalsIgnoreCase("bowler")
                    ? String.valueOf(p.getWickets())
                    : "N/A";
            String economy = p.getRole().equalsIgnoreCase("bowler")
                    ? String.valueOf(p.getEconomy())
                    : "N/A";

            Object[] row = {
                p.getId(),
                p.getName(),
                p.getRole(),
                p.getRuns(),
                wickets,
                p.getMatches(),
                p.getAverage(),
                p.getStrikeRate(),
                economy,
                p.getTeamName()
            };
            model.addRow(row);
        }
        System.out.println("Player table refreshed with " + model.getRowCount() + " records.");
    }
}
