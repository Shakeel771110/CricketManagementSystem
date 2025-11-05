/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author N TECH
 */
//import console_version.Player;


package console_version;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueManager {
    private Queue<Player> playerQueue = new LinkedList<>();

    // ➕ Add player
    public void enqueue(Player player) {
        playerQueue.add(player);
        System.out.println("Player added successfully!");
    }

    // ➖ Remove player
    public void dequeue() {
        if (playerQueue.isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            Player removed = playerQueue.remove();
            System.out.println("Removed Player: " + removed.getName());
        }
    }

    // 👁️ Display all players
    public void display() {
        if (playerQueue.isEmpty()) {
            System.out.println("No players in queue!");
            return;
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-4s | %-12s | %-9s | %-6s | %-8s | %-8s | %-6s | %-6s | %-6s | %-10s%n",
                "ID", "Name", "Role", "Runs", "Wickets", "Matches", "Avg", "SR", "Eco", "Team");
        System.out.println("---------------------------------------------------------------");

        for (Player player : playerQueue) {
            String wickets = player.getRole().equalsIgnoreCase("bowler") ? String.valueOf(player.getWickets()) : "N/A";
            String economy = player.getRole().equalsIgnoreCase("bowler") ? String.valueOf(player.getEconomy()) : "N/A";

            System.out.printf("%-4d | %-12s | %-9s | %-6d | %-8s | %-8d | %-6.1f | %-6.1f | %-6s | %-10s%n",
                    player.getId(),
                    player.getName(),
                    player.getRole(),
                    player.getRuns(),
                    wickets,
                    player.getMatches(),
                    player.getAverage(),
                    player.getStrikeRate(),
                    economy,
                    player.getTeamName());
        }

        System.out.println("---------------------------------------------------------------\n");
    }

    // 🔄 Update player (all fields)
    public void update(int id, Scanner sc) {
        Player p = findPlayerById(id);
        if (p == null) {
            System.out.println("Player ID not found!");
            return;
        }

        try {
            System.out.print("Enter Name: ");
            p.setName(sc.nextLine());
            System.out.print("Enter Role: ");
            p.setRole(sc.nextLine());
            System.out.print("Enter Runs: ");
            p.setRuns(sc.nextInt());
            System.out.print("Enter Wickets: ");
            p.setWickets(sc.nextInt());
            System.out.print("Enter Matches: ");
            p.setMatches(sc.nextInt());
            System.out.print("Enter Average: ");
            p.setAverage(sc.nextDouble());
            System.out.print("Enter Strike Rate: ");
            p.setStrikeRate(sc.nextDouble());
            System.out.print("Enter Economy: ");
            p.setEconomy(sc.nextDouble());
            sc.nextLine(); // clear buffer
            System.out.print("Enter Team Name: ");
            p.setTeamName(sc.nextLine());

            System.out.println("Player updated successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input! Update aborted.");
            sc.nextLine(); // clear buffer on error
        }
    }

    // 🔍 Find player by ID
    public Player findPlayerById(int id) {
        for (Player p : playerQueue) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // 👀 Peek
    public Player peekPlayer() {
        return playerQueue.peek();
    }

    // ✅ Check if queue is empty
    public boolean isEmpty() {
        return playerQueue.isEmpty();
    }

    // Getter & Setter for file handling
    public Queue<Player> getQueue() { return playerQueue; }
    public void setQueue(Queue<Player> queue) { this.playerQueue = queue; }
}
