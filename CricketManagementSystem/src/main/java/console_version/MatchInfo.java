/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console_version;

/**
 *
 * Updated MatchInfo for GUI auto-ID support
 */
public class MatchInfo {
    private static int idCounter = 1; // auto increment counter
    private int matchId;
    private String team1;
    private String team2;
    private String venue;
    private String date;     // format: "12-Nov-2025"
    private String result;   // "Pending", "Team A Won", etc.

    // === Constructor with auto ID ===
    public MatchInfo(String team1, String team2, String venue, String date, String result) {
        this.matchId = idCounter++;
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.date = date;
        this.result = result;
    }

    // === Constructor with explicit ID ===
    public MatchInfo(int matchId, String team1, String team2, String venue, String date, String result) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.date = date;
        this.result = result;

        // Ensure idCounter is always ahead of manually set IDs
        if (matchId >= idCounter) {
            idCounter = matchId + 1;
        }
    }

    // === Setter for loading data (optional) ===
    public void setMatchIdForLoading(int id) {
        this.matchId = id;
        if (id >= idCounter) idCounter = id + 1;
    }

    // === Getters & Setters ===
    public int getMatchId() {
        return matchId;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Match ID: " + matchId +
               "\nTeams: " + team1 + " vs " + team2 +
               "\nVenue: " + venue +
               "\nDate: " + date +
               "\nResult: " + result + "\n";
    }

    // === Static helper to get next auto-generated ID without creating object ===
    public static int getNextId() {
        return idCounter;
    }
}
