/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console_version;

public class Player {
    private static int nextId = 1;   // Auto-generated ID tracker
    private int id;                  // Auto-assigned when player created
    private String name;             // Player name
    private String role;             // Batsman / Bowler / All-Rounder
    private int runs;                // Total runs scored
    private int wickets;             // Total wickets taken
    private int matches;             // Matches played
    private double average;          // Batting or bowling average (manual input)
    private double strikeRate;       // Batting strike rate (manual input)
    private double economy;          // Bowling economy (manual input)
    private String teamName;         // Team name

    // Constructor
    public Player(String name, String role, int runs, int wickets, int matches,
                  double average, double strikeRate, double economy, String teamName) {
        this.id = nextId++;
        this.name = name;
        this.role = role;
        this.runs = runs;
        this.wickets = wickets;
        this.matches = matches;
        this.average = average;
        this.strikeRate = strikeRate;
        this.economy = economy;
        this.teamName = teamName;
    }

    // ✅ Special setter for loading from file
    public void setIdForLoading(int id) {
        this.id = id;
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public static int getNextId() { return nextId; }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getRuns() { return runs; }
    public void setRuns(int runs) { this.runs = runs; }
    public int getWickets() { return wickets; }
    public void setWickets(int wickets) { this.wickets = wickets; }
    public int getMatches() { return matches; }
    public void setMatches(int matches) { this.matches = matches; }
    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
    public double getStrikeRate() { return strikeRate; }
    public void setStrikeRate(double strikeRate) { this.strikeRate = strikeRate; }
    public double getEconomy() { return economy; }
    public void setEconomy(double economy) { this.economy = economy; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    // toString() display
    @Override
    public String toString() {
        return "ID: " + id +
               " | Name: " + name +
               " | Role: " + role +
               " | Runs: " + runs +
               " | Wickets: " + (wickets == 0 ? "N/A" : wickets) +
               " | Matches: " + matches +
               " | Avg: " + (average == 0 ? "N/A" : average) +
               " | SR: " + (strikeRate == 0 ? "N/A" : strikeRate) +
               " | Economy: " + (economy == 0 ? "N/A" : economy) +
               " | Team: " + teamName;
    }
}
