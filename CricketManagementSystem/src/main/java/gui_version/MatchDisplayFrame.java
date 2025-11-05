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

public class MatchDisplayFrame extends JFrame {

    private MatchManager matchManager;
    private JTable table;
    private DefaultTableModel model;

    public MatchDisplayFrame(MatchManager manager) {
        this.matchManager = manager;

        setTitle("Match Schedules & Results");
        setSize(950, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Table Columns
        String[] columns = {"Match ID", "Team 1", "Team 2", "Venue", "Date", "Result"};

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table non-editable
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

        refresh();
        setVisible(true);
    }

    // Refresh method
    public void refresh() {
        model.setRowCount(0);

        if (matchManager == null || matchManager.getQueue() == null) {
            System.out.println("No match data to refresh.");
            return;
        }

        Queue<MatchInfo> queue = matchManager.getQueue();
        for (MatchInfo m : queue) {
            Object[] row = {
                m.getMatchId(),
                m.getTeam1(),
                m.getTeam2(),
                m.getVenue(),
                m.getDate(),
                m.getResult()
            };
            model.addRow(row);
        }

        System.out.println("Match table refreshed with " + model.getRowCount() + " records.");
    }
}
