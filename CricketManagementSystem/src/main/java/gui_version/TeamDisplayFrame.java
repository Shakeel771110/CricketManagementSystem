/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author N TECH
 */
package gui_version;

import console_version.TeamManager;
import console_version.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TeamDisplayFrame extends JFrame {

    private final TeamManager teamManager;
    private final JTable table;
    private final DefaultTableModel tableModel;

    public TeamDisplayFrame(TeamManager manager) {
        this.teamManager = manager;

        setTitle("All Teams");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table setup
        String[] columns = {"ID", "Team Name", "Captain", "Matches", "Wins", "Win %"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Initial load
        refresh();

        setVisible(true);
    }

    // Refresh table data
    public void refresh() {
        tableModel.setRowCount(0); // clear old data
        for (Team t : teamManager.getQueue()) {
            tableModel.addRow(new Object[]{
                    t.getTeamId(),
                    t.getTeamName(),
                    t.getCaptainName(),
                    t.getMatchesPlayed(),
                    t.getWins(),
                    String.format("%.2f", t.getWinPercentage())
            });
        }
    }
}
