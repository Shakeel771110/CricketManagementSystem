/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console_version;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatchManager {
    private Queue<MatchInfo> matchQueue = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    // === Add Match (Console version) ===
    public void addMatch() {
        System.out.print("Enter Team 1: ");
        String team1 = sc.nextLine();

        System.out.print("Enter Team 2: ");
        String team2 = sc.nextLine();

        System.out.print("Enter Venue: ");
        String venue = sc.nextLine();

        System.out.print("Enter Date (e.g., 12-Nov-2025): ");
        String date = sc.nextLine();

        System.out.print("Enter Result (or 'Pending'): ");
        String result = sc.nextLine();

        MatchInfo match = new MatchInfo(team1, team2, venue, date, result);
        matchQueue.add(match);

        System.out.println("Match Added Successfully!\n");
    }

    // === Display All Matches ===
    public void displayMatches() {
        if (matchQueue.isEmpty()) {
            System.out.println("⚠️ No matches available.\n");
            return;
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-15s | %-10s | %-12s | %-10s%n",
                "ID", "Team 1", "Team 2", "Venue", "Date", "Result");
        System.out.println("---------------------------------------------------------------");

        for (MatchInfo match : matchQueue) {
            System.out.printf("%-5d | %-15s | %-15s | %-10s | %-12s | %-10s%n",
                    match.getMatchId(),
                    match.getTeam1(),
                    match.getTeam2(),
                    match.getVenue(),
                    match.getDate(),
                    match.getResult());
        }

        System.out.println("---------------------------------------------------------------\n");
    }

    // === Remove First Match ===
    public void removeMatch() {
        if (matchQueue.isEmpty()) {
            System.out.println("No matches to remove.\n");
            return;
        }

        MatchInfo removed = matchQueue.remove();
        System.out.println("Removed Match ID: " + removed.getMatchId() + "\n");
    }

    // === Peek First Match ===
    public void peekMatch() {
        if (matchQueue.isEmpty()) {
            System.out.println("Queue is empty.\n");
            return;
        }

        System.out.println("First Match in Queue:\n" + matchQueue.peek());
    }

    // === Update Match (Console version) ===
    public void updateMatch() {
        if (matchQueue.isEmpty()) {
            System.out.println("No matches to update.\n");
            return;
        }

        System.out.print("Enter Match ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer

        boolean found = false;
        for (MatchInfo match : matchQueue) {
            if (match.getMatchId() == id) {
                found = true;
                System.out.println("What do you want to update?");
                System.out.println("1. Team 1");
                System.out.println("2. Team 2");
                System.out.println("3. Venue");
                System.out.println("4. Date");
                System.out.println("5. Result");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1 -> { match.setTeam1(sc.nextLine()); System.out.println("Team 1 Updated!"); }
                    case 2 -> { match.setTeam2(sc.nextLine()); System.out.println("Team 2 Updated!"); }
                    case 3 -> { match.setVenue(sc.nextLine()); System.out.println("Venue Updated!"); }
                    case 4 -> { match.setDate(sc.nextLine()); System.out.println("Date Updated!"); }
                    case 5 -> { match.setResult(sc.nextLine()); System.out.println("Result Updated!"); }
                    default -> System.out.println("Invalid Option!");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Match ID not found.\n");
        }
    }

    // === GUI: Get Queue ===
    public Queue<MatchInfo> getQueue() {
        return matchQueue;
    }

    public void setQueue(Queue<MatchInfo> q) {
        this.matchQueue = q;
    }

    // === GUI: Get Next Match ID ===
    public int getNextMatchId() {
        return MatchInfo.getNextId();
    }
}
