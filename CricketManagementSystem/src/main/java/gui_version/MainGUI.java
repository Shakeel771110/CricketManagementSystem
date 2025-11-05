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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {

    public QueueManager playerManager;
    public MatchManager matchManager;
    public TeamManager teamManager;

    private PlayerFrame playerFrame;
    private MatchFrame matchFrame;
    private TeamFrame teamFrame;

    public MainGUI() {
        playerManager = new QueueManager();
        matchManager = new MatchManager();
        teamManager = new TeamManager();

        setTitle("Cricket Management Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // === Background Panel ===
        JPanel backgroundPanel = new JPanel() {
            Image bg = new ImageIcon("C:\\Users\\N TECH\\Documents\\NetBeansProjects\\CricketManagementSystem\\src\\main\\java\\gui_version\\background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // ===== Load Data =====
        loadAllData();

        // ===== Buttons =====
        JButton btnPlayer = createButton("Player Statistics");
        JButton btnMatch = createButton("Match Schedule");
        JButton btnTeam = createButton("Team Management");
        JButton btnSave = createButton("Save All Data");

        // ===== Button Positions =====
        btnPlayer.setBounds(600, 200, 350, 80);
        btnMatch.setBounds(600, 320, 350, 80);
        btnTeam.setBounds(600, 440, 350, 80);
        btnSave.setBounds(600, 560, 350, 80);

        // ===== Add Buttons =====
        add(btnPlayer);
        add(btnMatch);
        add(btnTeam);
        add(btnSave);

        // ===== Button Actions =====
        btnPlayer.addActionListener(e -> openPlayerFrame());
        btnMatch.addActionListener(e -> openMatchFrame());
        btnTeam.addActionListener(e -> openTeamFrame());
        btnSave.addActionListener(e -> saveAllData());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setBackground(new Color(0, 173, 181, 230));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 173, 181, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 173, 181, 230));
            }
        });
        return button;
    }

    private void openPlayerFrame() {
        if (playerFrame == null || !playerFrame.isDisplayable()) {
            playerFrame = new PlayerFrame();
            playerFrame.playerManager = this.playerManager;
            playerFrame.setVisible(true);
            dispose();
        } else {
            playerFrame.toFront();
            playerFrame.refresh();
        }
    }

    private void openMatchFrame() {
        if (matchFrame == null || !matchFrame.isDisplayable()) {
            matchFrame = new MatchFrame();
            matchFrame.matchManager = this.matchManager;
            matchFrame.setVisible(true);
            dispose();
        } else {
            matchFrame.toFront();
            matchFrame.refresh();
        }
    }

    private void openTeamFrame() {
        if (teamFrame == null || !teamFrame.isDisplayable()) {
            teamFrame = new TeamFrame();
            teamFrame.teamManager = this.teamManager;
            teamFrame.setVisible(true);
            dispose();
        } else {
            teamFrame.toFront();
            teamFrame.refresh();
        }
    }

    private void loadAllData() {
        try {
            playerManager.setQueue(FileHandler.loadPlayers());
            matchManager.setQueue(FileHandler.loadMatches());
            teamManager.setQueue(FileHandler.loadTeams());
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void saveAllData() {
        try {
            FileHandler.savePlayers(playerManager.getQueue());
            FileHandler.saveMatches(matchManager.getQueue());
            FileHandler.saveTeams(teamManager.getQueue());
            JOptionPane.showMessageDialog(this, "All data saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
