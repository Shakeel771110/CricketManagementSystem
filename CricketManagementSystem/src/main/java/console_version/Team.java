/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author N TECH
 */
package console_version;

public class Team {
    private static int nextId = 1;       // Auto-generated team ID
    private int teamId;
    private String teamName;
    private String captainName;
    private int matchesPlayed;
    private int wins;

    // Constructor
    public Team(String teamName, String captainName, int matchesPlayed, int wins) {
        this.teamId = nextId++;
        this.teamName = teamName;
        this.captainName = captainName;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
    }

    // For file loading
    public void setTeamIdForLoading(int id) {
        this.teamId = id;
        if (id >= nextId) nextId = id + 1;
    }

    // Getters & Setters
    public int getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getCaptainName() { return captainName; }
    public void setCaptainName(String captainName) { this.captainName = captainName; }
    public int getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(int matchesPlayed) { this.matchesPlayed = matchesPlayed; }
    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    // Calculate win percentage
    public double getWinPercentage() {
        if (matchesPlayed == 0) return 0;
        return (wins * 100.0) / matchesPlayed;
    }

    @Override
    public String toString() {
        return "Team ID: " + teamId +
               "\nTeam Name: " + teamName +
               "\nCaptain: " + captainName +
               "\nMatches Played: " + matchesPlayed +
               "\nWins: " + wins +
               "\nWin %: " + String.format("%.2f", getWinPercentage()) + "%\n";
    }

    // ===== New method to get the next team ID =====
    public static int getNextId() {
        return nextId;
    }
}
