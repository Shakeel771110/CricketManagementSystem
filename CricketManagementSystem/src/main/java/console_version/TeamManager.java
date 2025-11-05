/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author N TECH
 */
package console_version;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TeamManager {
    private Queue<Team> teamQueue = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    // Add Team
    public void addTeam() {
        System.out.print("Enter Team Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Captain Name: ");
        String captain = sc.nextLine();

        System.out.print("Enter Matches Played: ");
        int matches = sc.nextInt();

        System.out.print("Enter Wins: ");
        int wins = sc.nextInt();
        sc.nextLine(); // clear buffer

        Team team = new Team(name, captain, matches, wins);
        teamQueue.add(team);
        System.out.println("Team added successfully!\n");
    }

    // ===== New method for GUI to get next team ID =====
    public int getNextTeamId() {
        return Team.getNextId();
    }

    // Other existing methods
    public void displayTeams() { /* same as before */ }
    public void removeTeam() { /* same as before */ }
    public void peekTeam() { /* same as before */ }
    public void updateTeam(int id) { /* same as before */ }

    public boolean isEmpty() { return teamQueue.isEmpty(); }
    public Queue<Team> getQueue() { return teamQueue; }
    public void setQueue(Queue<Team> q) { this.teamQueue = q; }
}
