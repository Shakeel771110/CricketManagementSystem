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
import java.awt.*;

public class TeamFrame extends JFrame {

    public TeamManager teamManager;
    private JTextField txtTeamName, txtCaptain, txtMatches, txtWins;
    private JLabel lblTeamId;
    private TeamDisplayFrame displayFrame;

    public TeamFrame() {
        teamManager = new TeamManager();

        setTitle("Team Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ----- Background Panel -----
        JPanel backgroundPanel = new JPanel() {
            Image bg = new ImageIcon("C:\\Users\\N TECH\\Documents\\NetBeansProjects\\CricketManagementSystem\\src\\main\\java\\gui_version\\background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // ----- Title -----
        JLabel title = new JLabel("TEAM MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setOpaque(true);
        title.setBackground(new Color(34, 40, 49));
        title.setForeground(Color.WHITE);
        backgroundPanel.add(title, BorderLayout.NORTH);

        // ----- Form Panel -----
        JPanel formPanel = new JPanel(null);
        formPanel.setOpaque(false); // Transparent to show background

        Font lblFont = new Font("Segoe UI", Font.BOLD, 16);
        Font txtFont = new Font("Segoe UI", Font.PLAIN, 16);

        int x1 = 500, x2 = 700, y = 120, gapY = 50, height = 30;

        JLabel[] labels = {
            new JLabel("Team ID:"),
            new JLabel("Team Name:"),
            new JLabel("Captain:"),
            new JLabel("Matches Played:"),
            new JLabel("Wins:")
        };

        lblTeamId = new JLabel(String.valueOf(teamManager.getNextTeamId()));
        lblTeamId.setFont(txtFont);
        lblTeamId.setForeground(Color.LIGHT_GRAY);

        txtTeamName = new JTextField();
        txtCaptain = new JTextField();
        txtMatches = new JTextField();
        txtWins = new JTextField();

        JTextField[] fields = {null, txtTeamName, txtCaptain, txtMatches, txtWins};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(lblFont);
            labels[i].setForeground(Color.WHITE);
            labels[i].setBounds(x1, y + (i * gapY), 150, height);
            formPanel.add(labels[i]);

            if (i == 0) {
                lblTeamId.setBounds(x2, y, 200, height);
                formPanel.add(lblTeamId);
            } else {
                fields[i].setFont(txtFont);
                fields[i].setBounds(x2, y + (i * gapY), 200, height);
                formPanel.add(fields[i]);
            }
        }

        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        // ----- Button Panel -----
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(34, 40, 49, 200)); // semi-transparent
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));

        String[] btnNames = {"Add", "Update", "Remove", "Peek", "Display All", "Exit"};
        JButton[] buttons = new JButton[btnNames.length];

        for (int i = 0; i < btnNames.length; i++) {
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
            buttons[i].setBackground(new Color(0, 173, 181));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setPreferredSize(new Dimension(150, 40));
            btnPanel.add(buttons[i]);
        }

        backgroundPanel.add(btnPanel, BorderLayout.SOUTH);

        // ----- Button Actions -----
        buttons[0].addActionListener(e -> addTeam());
        buttons[1].addActionListener(e -> updateTeam());
        buttons[2].addActionListener(e -> removeTeam());
        buttons[3].addActionListener(e -> peekTeam());
        buttons[4].addActionListener(e -> openDisplayFrame());
        buttons[5].addActionListener(e -> {
            this.dispose();
            new MainGUI().setVisible(true);
        });
    }

    // ===== Add Team =====
    private void addTeam() {
        try {
            String name = txtTeamName.getText();
            String captain = txtCaptain.getText();
            int matches = Integer.parseInt(txtMatches.getText());
            int wins = Integer.parseInt(txtWins.getText());

            if (name.isEmpty() || captain.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            Team team = new Team(name, captain, matches, wins);
            teamManager.getQueue().add(team);
            JOptionPane.showMessageDialog(this, "Team Added Successfully!");
            clearFields();

            if (displayFrame != null) displayFrame.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    // ===== Update Team =====
    private void updateTeam() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Team ID to Update:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            boolean found = false;
            for (Team t : teamManager.getQueue()) {
                if (t.getTeamId() == id) {
                    found = true;
                    t.setTeamName(txtTeamName.getText());
                    t.setCaptainName(txtCaptain.getText());
                    t.setMatchesPlayed(Integer.parseInt(txtMatches.getText()));
                    t.setWins(Integer.parseInt(txtWins.getText()));
                    JOptionPane.showMessageDialog(this, "Team Updated!");
                    break;
                }
            }

            if (!found) JOptionPane.showMessageDialog(this, "Team ID not found!");
            if (displayFrame != null) displayFrame.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    // ===== Remove Team =====
    private void removeTeam() {
        if (teamManager.getQueue().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Teams to Remove!");
            return;
        }
        Team removed = teamManager.getQueue().remove();
        JOptionPane.showMessageDialog(this, "Removed Team ID: " + removed.getTeamId());
        if (displayFrame != null) displayFrame.refresh();
    }

    // ===== Peek Team =====
    private void peekTeam() {
        if (teamManager.getQueue().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Queue is Empty!");
            return;
        }
        Team t = teamManager.getQueue().peek();
        JOptionPane.showMessageDialog(this,
                "Next Team:\nTeam Name: " + t.getTeamName() +
                "\nCaptain: " + t.getCaptainName() +
                "\nMatches Played: " + t.getMatchesPlayed() +
                "\nWins: " + t.getWins() +
                "\nWin %: " + String.format("%.2f", t.getWinPercentage()) + "%");
    }

    // ===== Display Frame =====
    private void openDisplayFrame() {
        try {
            teamManager.setQueue(FileHandler.loadTeams());
        } catch (Exception e) {
            System.out.println("Error loading teams: " + e.getMessage());
        }

        if (displayFrame == null) {
            displayFrame = new TeamDisplayFrame(teamManager);
        } else {
            displayFrame.toFront();
            displayFrame.refresh();
        }
    }

    // ===== Clear Fields =====
    private void clearFields() {
        txtTeamName.setText("");
        txtCaptain.setText("");
        txtMatches.setText("");
        txtWins.setText("");
        lblTeamId.setText(String.valueOf(teamManager.getNextTeamId()));
    }

    public void refresh() {
        if (displayFrame != null) {
            displayFrame.refresh();
        }
    }

    // ===== Main =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TeamFrame().setVisible(true));
    }
}
