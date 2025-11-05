/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author N TECH
 */

package console_version;

import java.util.Scanner;

public class CricketManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueueManager playerManager = new QueueManager();
        MatchManager matchManager = new MatchManager();
        TeamManager teamManager = new TeamManager();

        // 🔹 Load data from files
        playerManager.setQueue(FileHandler.loadPlayers());
        matchManager.setQueue(FileHandler.loadMatches());
        teamManager.setQueue(FileHandler.loadTeams());

        while (true) {
            System.out.println("\n===== Cricket Management System =====");
            System.out.println("1. Player Statistics");
            System.out.println("2. Match Schedules");
            System.out.println("3. Team Management");
            System.out.println("4. Save Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    playerMenu(playerManager, sc);
                    break;

                case 2:
                    matchMenu(matchManager, sc);
                    break;

                case 3:
                    teamMenu(teamManager, sc);
                    break;

                case 4:
                    try {
                        FileHandler.savePlayers(playerManager.getQueue());
                        FileHandler.saveMatches(matchManager.getQueue());
                        FileHandler.saveTeams(teamManager.getQueue());
                        System.out.println("Data saved successfully!");
                    } catch (Exception e) {
                        System.out.println("Error saving files: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting System... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ======================= PLAYER MENU ==========================
    private static void playerMenu(QueueManager pm, Scanner sc) {
        while (true) {
            System.out.println("\n===== Player Management =====");
            System.out.println("1. Add Player");
            System.out.println("2. Display Players");
            System.out.println("3. Update Player");
            System.out.println("4. Remove Player");
            System.out.println("5. Peek (Show First Player)");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1:
                    System.out.println("Player ID: " + Player.getNextId());
                    System.out.print("Enter Player Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Role (Batsman / Bowler / All-Rounder): ");
                    String role = sc.nextLine();
                    System.out.print("Enter Runs: ");
                    int runs = sc.nextInt();
                    System.out.print("Enter Wickets: ");
                    int wickets = sc.nextInt();
                    System.out.print("Enter Matches Played: ");
                    int matches = sc.nextInt();
                    System.out.print("Enter Batting Average: ");
                    double avg = sc.nextDouble();
                    System.out.print("Enter Strike Rate: ");
                    double strikeRate = sc.nextDouble();
                    System.out.print("Enter Economy : ");
                    double economy = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Team Name: ");
                    String team = sc.nextLine();

                    Player p = new Player(name, role, runs, wickets, matches, avg, strikeRate, economy, team);
                    pm.enqueue(p);
                    System.out.println("✅ Player Added Successfully!");
                    break;

                case 2:
                    pm.display();
                    break;

                case 3:
                    System.out.print("Enter Player ID to Update: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // flush

                    Player player = pm.findPlayerById(id);
                    if (player == null) {
                        System.out.println("Player ID not found!");
                        break;
                    }

                    System.out.print("Enter new Name: ");
                    player.setName(sc.nextLine());

                    System.out.print("Enter new Role: ");
                    player.setRole(sc.nextLine());

                    System.out.print("Enter new Runs: ");
                    player.setRuns(sc.nextInt());

                    System.out.print("Enter new Wickets: ");
                    player.setWickets(sc.nextInt());

                    System.out.print("Enter new Matches: ");
                    player.setMatches(sc.nextInt());

                    System.out.print("Enter new Average: ");
                    player.setAverage(sc.nextDouble());

                    System.out.print("Enter new Strike Rate: ");
                    player.setStrikeRate(sc.nextDouble());

                    System.out.print("Enter new Economy: ");
                    player.setEconomy(sc.nextDouble());
                    sc.nextLine(); // flush newline

                    System.out.print("Enter new Team Name: ");
                    player.setTeamName(sc.nextLine());

                    System.out.println("✅ Player Updated Successfully!");
                    break;

                case 4:
                    pm.dequeue();
                    break;

                case 5:
                    Player first = pm.peekPlayer();
                    if (first == null) {
                        System.out.println("Queue is empty!");
                    } else {
                        System.out.println("Next Player:");
                        System.out.println(first);
                    }
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ======================= MATCH MENU ==========================
    private static void matchMenu(MatchManager mm, Scanner sc) {
        while (true) {
            System.out.println("\n===== Match Management =====");
            System.out.println("1. Add Match");
            System.out.println("2. Display Matches");
            System.out.println("3. Update Match");
            System.out.println("4. Remove Match");
            System.out.println("5. Peek (Show First Match)");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1:
                    mm.addMatch();
                    break;
                case 2:
                    mm.displayMatches();
                    break;
                case 3:
                    mm.updateMatch();
                    break;
                case 4:
                    mm.removeMatch();
                    break;
                case 5:
                    mm.peekMatch();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ======================= TEAM MENU ==========================
    private static void teamMenu(TeamManager tm, Scanner sc) {
        while (true) {
            System.out.println("\n===== Team Management =====");
            System.out.println("1. Add Team");
            System.out.println("2. Display Teams");
            System.out.println("3. Remove Team");
            System.out.println("4. Peek (Show First Team)");
            System.out.println("5. Update Team");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    tm.addTeam();
                    break;
                case 2:
                    tm.displayTeams();
                    break;
                case 3:
                    tm.removeTeam();
                    break;
                case 4:
                    tm.peekTeam();
                    break;
                case 5:
                    System.out.print("Enter Team ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    tm.updateTeam(id);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
