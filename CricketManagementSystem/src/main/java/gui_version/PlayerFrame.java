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

public class PlayerFrame extends JFrame {

    public QueueManager playerManager;
    private JTextField txtName, txtRole, txtRuns, txtWickets, txtMatches, txtAverage, txtStrikeRate, txtEconomy, txtTeam;
    private JLabel lblPlayerId;
    private PlayerDisplayFrame displayFrame;

    public PlayerFrame() {
        playerManager = new QueueManager();

        setTitle("Player Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== Background Panel =====
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

        // ===== Title Label =====
        JLabel title = new JLabel("PLAYER MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setOpaque(true);
        title.setBackground(new Color(34, 40, 49));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 1600, 60); // Adjust width according to screen
        backgroundPanel.add(title);

        // ===== Form Fields =====
        Font lblFont = new Font("Segoe UI", Font.BOLD, 16);
        Font txtFont = new Font("Segoe UI", Font.PLAIN, 16);

        int x1 = 500, x2 = 700, y = 100, gapY = 50, height = 30;

        JLabel[] labels = {
            new JLabel("Player ID:"), new JLabel("Name:"), new JLabel("Role:"),
            new JLabel("Runs:"), new JLabel("Wickets:"), new JLabel("Matches:"),
            new JLabel("Average:"), new JLabel("Strike Rate:"), new JLabel("Economy:"), new JLabel("Team:")
        };

        lblPlayerId = new JLabel(String.valueOf(Player.getNextId()));
        lblPlayerId.setFont(txtFont);
        lblPlayerId.setForeground(Color.LIGHT_GRAY);

        txtName = new JTextField(); txtRole = new JTextField();
        txtRuns = new JTextField(); txtWickets = new JTextField(); txtMatches = new JTextField();
        txtAverage = new JTextField(); txtStrikeRate = new JTextField(); txtEconomy = new JTextField();
        txtTeam = new JTextField();

        JTextField[] fields = {null, txtName, txtRole, txtRuns, txtWickets, txtMatches, txtAverage, txtStrikeRate, txtEconomy, txtTeam};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(lblFont);
            labels[i].setForeground(Color.WHITE);
            labels[i].setBounds(x1, y + (i * gapY), 150, height);
            backgroundPanel.add(labels[i]);

            if (i == 0) {
                lblPlayerId.setBounds(x2, y, 200, height);
                backgroundPanel.add(lblPlayerId);
            } else {
                fields[i].setFont(txtFont);
                fields[i].setBounds(x2, y + (i * gapY), 200, height);
                backgroundPanel.add(fields[i]);
            }
        }

        // ===== Buttons =====
        String[] btnNames = {"Add", "Update", "Remove", "Peek", "Display All", "Exit"};
        JButton[] buttons = new JButton[btnNames.length];

        int btnX = 500, btnY = y + (labels.length * gapY) + 30;
        for (int i = 0; i < btnNames.length; i++) {
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
            buttons[i].setBackground(new Color(0, 173, 181));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setBounds(btnX + (i * 170), btnY, 150, 40); // Spacing buttons
            backgroundPanel.add(buttons[i]);
        }

        // ===== Button Actions =====
        buttons[0].addActionListener(e -> addPlayer());
        buttons[1].addActionListener(e -> updatePlayer());
        buttons[2].addActionListener(e -> removePlayer());
        buttons[3].addActionListener(e -> peekPlayer());
        buttons[4].addActionListener(e -> openDisplayFrame());
        buttons[5].addActionListener(e -> {
            this.dispose(); // Close PlayerFrame
            new MainGUI().setVisible(true); // Reopen main dashboard
        });
    }

    // ===== Methods =====
    private void addPlayer() {
        try {
            String name = txtName.getText();
            String role = txtRole.getText();
            int runs = Integer.parseInt(txtRuns.getText());
            int wickets = Integer.parseInt(txtWickets.getText());
            int matches = Integer.parseInt(txtMatches.getText());
            double avg = Double.parseDouble(txtAverage.getText());
            double strikeRate = Double.parseDouble(txtStrikeRate.getText());
            double economy = Double.parseDouble(txtEconomy.getText());
            String team = txtTeam.getText();

            if (name.isEmpty() || role.isEmpty() || team.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            Player p = new Player(name, role, runs, wickets, matches, avg, strikeRate, economy, team);
            playerManager.enqueue(p);
            JOptionPane.showMessageDialog(this, "✅ Player Added Successfully!");
            clearFields();

            if (displayFrame != null && displayFrame.isDisplayable()) {
                displayFrame.refresh();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Invalid Input!");
        }
    }

    private void updatePlayer() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Player ID to Update:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            Player p = playerManager.findPlayerById(id);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Player ID not found!");
                return;
            }

            p.setName(txtName.getText());
            p.setRole(txtRole.getText());
            p.setRuns(Integer.parseInt(txtRuns.getText()));
            p.setWickets(Integer.parseInt(txtWickets.getText()));
            p.setMatches(Integer.parseInt(txtMatches.getText()));
            p.setAverage(Double.parseDouble(txtAverage.getText()));
            p.setStrikeRate(Double.parseDouble(txtStrikeRate.getText()));
            p.setEconomy(Double.parseDouble(txtEconomy.getText()));
            p.setTeamName(txtTeam.getText());

            JOptionPane.showMessageDialog(this, "✅ Player Updated!");
            if (displayFrame != null && displayFrame.isDisplayable()) {
                displayFrame.refresh();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating player!");
        }
    }

    private void removePlayer() {
        if (playerManager.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Players to Remove!");
            return;
        }
        playerManager.dequeue();
        if (displayFrame != null && displayFrame.isDisplayable()) {
            displayFrame.refresh();
        }
    }

    private void peekPlayer() {
        Player p = playerManager.peekPlayer();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Queue is Empty!");
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Next Player:\nName: " + p.getName() +
                        "\nRole: " + p.getRole() +
                        "\nMatches Played: " + p.getMatches());
    }

    private void openDisplayFrame() {
        if (displayFrame == null || !displayFrame.isDisplayable()) {
            displayFrame = new PlayerDisplayFrame(playerManager);
        } else {
            displayFrame.toFront();
            displayFrame.refresh();
        }
    }

    private void clearFields() {
        txtName.setText(""); txtRole.setText(""); txtRuns.setText(""); txtWickets.setText("");
        txtMatches.setText(""); txtAverage.setText(""); txtStrikeRate.setText(""); txtEconomy.setText("");
        txtTeam.setText("");
        lblPlayerId.setText(String.valueOf(Player.getNextId()));
    }

    public void refresh() {
        if (displayFrame != null) {
            displayFrame.refresh();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerFrame().setVisible(true));
    }
}
