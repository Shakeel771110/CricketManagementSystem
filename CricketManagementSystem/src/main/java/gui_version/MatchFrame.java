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

public class MatchFrame extends JFrame {

    public MatchManager matchManager;
    private JTextField txtTeam1, txtTeam2, txtVenue, txtDate, txtResult;
    private JLabel lblMatchId;
    private MatchDisplayFrame displayFrame;

    public MatchFrame() {
        matchManager = new MatchManager();

        setTitle("Match Management");
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
        JLabel title = new JLabel("MATCH MANAGEMENT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setOpaque(true);
        title.setBackground(new Color(34, 40, 49));
        title.setForeground(Color.WHITE);
        backgroundPanel.add(title, BorderLayout.NORTH);

        // ----- Form Panel -----
        JPanel formPanel = new JPanel(null);
        formPanel.setOpaque(false); // transparent to show background

        Font lblFont = new Font("Segoe UI", Font.BOLD, 16);
        Font txtFont = new Font("Segoe UI", Font.PLAIN, 16);

        int x1 = 500, x2 = 700, y = 120, gapY = 50, height = 30;

        JLabel[] labels = {
            new JLabel("Match ID:"),
            new JLabel("Team 1:"),
            new JLabel("Team 2:"),
            new JLabel("Venue:"),
            new JLabel("Date:"),
            new JLabel("Result:")
        };

        lblMatchId = new JLabel(String.valueOf(matchManager.getNextMatchId()));
        lblMatchId.setFont(txtFont);
        lblMatchId.setForeground(Color.LIGHT_GRAY);

        txtTeam1 = new JTextField();
        txtTeam2 = new JTextField();
        txtVenue = new JTextField();
        txtDate = new JTextField();
        txtResult = new JTextField();

        JTextField[] fields = {null, txtTeam1, txtTeam2, txtVenue, txtDate, txtResult};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(lblFont);
            labels[i].setForeground(Color.WHITE);
            labels[i].setBounds(x1, y + (i * gapY), 150, height);
            formPanel.add(labels[i]);

            if (i == 0) {
                lblMatchId.setBounds(x2, y, 200, height);
                formPanel.add(lblMatchId);
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
        buttons[0].addActionListener(e -> addMatch());
        buttons[1].addActionListener(e -> updateMatch());
        buttons[2].addActionListener(e -> removeMatch());
        buttons[3].addActionListener(e -> peekMatch());
        buttons[4].addActionListener(e -> openDisplayFrame());
        buttons[5].addActionListener(e -> {
            this.dispose();
            new MainGUI().setVisible(true);
        });
    }

    // ===== Methods =====
    private void addMatch() {
        try {
            String team1 = txtTeam1.getText();
            String team2 = txtTeam2.getText();
            String venue = txtVenue.getText();
            String date = txtDate.getText();
            String result = txtResult.getText();
            if (team1.isEmpty() || team2.isEmpty() || venue.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }
            MatchInfo match = new MatchInfo(matchManager.getNextMatchId(), team1, team2, venue, date, result);
            matchManager.getQueue().add(match);
            JOptionPane.showMessageDialog(this, "Match Added Successfully!");
            clearFields();
            if (displayFrame != null) displayFrame.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void updateMatch() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Match ID to Update:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            boolean found = false;
            for (MatchInfo m : matchManager.getQueue()) {
                if (m.getMatchId() == id) {
                    m.setTeam1(txtTeam1.getText());
                    m.setTeam2(txtTeam2.getText());
                    m.setVenue(txtVenue.getText());
                    m.setDate(txtDate.getText());
                    m.setResult(txtResult.getText());
                    JOptionPane.showMessageDialog(this, "Match Updated!");
                    found = true;
                    break;
                }
            }
            if (!found) JOptionPane.showMessageDialog(this, "Match ID not found!");
            if (displayFrame != null) displayFrame.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID!");
        }
    }

    private void removeMatch() {
        if (matchManager.getQueue().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Matches to Remove!");
            return;
        }
        MatchInfo removed = matchManager.getQueue().remove();
        JOptionPane.showMessageDialog(this, "Removed Match ID: " + removed.getMatchId());
        if (displayFrame != null) displayFrame.refresh();
    }

    private void peekMatch() {
        if (matchManager.getQueue().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Queue is Empty!");
            return;
        }
        MatchInfo m = matchManager.getQueue().peek();
        JOptionPane.showMessageDialog(this,
                "Next Match:\nTeam 1: " + m.getTeam1() +
                "\nTeam 2: " + m.getTeam2() +
                "\nVenue: " + m.getVenue() +
                "\nDate: " + m.getDate() +
                "\nResult: " + m.getResult());
    }

    private void openDisplayFrame() {
        matchManager.setQueue(FileHandler.loadMatches());
        if (displayFrame == null) {
            displayFrame = new MatchDisplayFrame(matchManager);
            displayFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    displayFrame = null;
                }
            });
        } else {
            displayFrame.toFront();
            displayFrame.refresh();
        }
    }

    private void clearFields() {
        txtTeam1.setText("");
        txtTeam2.setText("");
        txtVenue.setText("");
        txtDate.setText("");
        txtResult.setText("");
        lblMatchId.setText(String.valueOf(matchManager.getNextMatchId()));
    }

    public void refresh() {
        if (displayFrame != null) displayFrame.refresh();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MatchFrame().setVisible(true));
    }
}
