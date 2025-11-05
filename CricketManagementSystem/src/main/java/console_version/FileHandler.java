/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console_version;

/**
 *
 * @author N TECH
 */


import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class FileHandler {
    // filenames (relative to project working directory)
    private static final String PLAYER_FILE = "players.txt";
    private static final String MATCH_FILE  = "matches.txt";
    private static final String TEAM_FILE   = "teams.txt";

    // ------------------- PLAYERS -------------------
    public static void savePlayers(Queue<Player> queue) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PLAYER_FILE))) {
            for (Player p : queue) {
                bw.write(p.getId() + "|" + p.getName() + "|" + p.getRole() + "|" +
                         p.getRuns() + "|" + p.getWickets() + "|" + p.getMatches() + "|" +
                         p.getAverage() + "|" + p.getStrikeRate() + "|" + p.getEconomy() + "|" +
                         p.getTeamName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving players: " + e.getMessage());
        }
    }

    public static Queue<Player> loadPlayers() {
        Queue<Player> queue = new LinkedList<>();
        File f = new File(PLAYER_FILE);
        if (!f.exists()) return queue;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length < 10) continue;
                int id = Integer.parseInt(p[0]);
                String name = p[1];
                String role = p[2];
                int runs = Integer.parseInt(p[3]);
                int wickets = Integer.parseInt(p[4]);
                int matches = Integer.parseInt(p[5]);
                double avg = Double.parseDouble(p[6]);
                double sr = Double.parseDouble(p[7]);
                double eco = Double.parseDouble(p[8]);
                String team = p[9];

                Player player = new Player(name, role, runs, wickets, matches, avg, sr, eco, team);
                player.setIdForLoading(id); // preserve ID and update nextId
                queue.add(player);
            }
        } catch (IOException e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
        return queue;
    }

    // ------------------- MATCHES -------------------
    public static void saveMatches(Queue<MatchInfo> queue) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MATCH_FILE))) {
            for (MatchInfo m : queue) {
                bw.write(m.getMatchId() + "|" + m.getTeam1() + "|" + m.getTeam2() + "|" +
                         m.getVenue() + "|" + m.getDate() + "|" + m.getResult());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving matches: " + e.getMessage());
        }
    }

    public static Queue<MatchInfo> loadMatches() {
        Queue<MatchInfo> queue = new LinkedList<>();
        File f = new File(MATCH_FILE);
        if (!f.exists()) return queue;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] m = line.split("\\|", -1);
                if (m.length < 6) continue;
                int id = Integer.parseInt(m[0]);
                String team1 = m[1];
                String team2 = m[2];
                String venue = m[3];
                String date  = m[4];
                String result= m[5];

                MatchInfo match = new MatchInfo(team1, team2, venue, date, result);
                match.setMatchIdForLoading(id);
                queue.add(match);
            }
        } catch (IOException e) {
            System.out.println("Error loading matches: " + e.getMessage());
        }
        return queue;
    }

    // ------------------- TEAMS -------------------
    public static void saveTeams(Queue<Team> queue) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEAM_FILE))) {
            for (Team t : queue) {
                bw.write(t.getTeamId() + "|" + t.getTeamName() + "|" + t.getCaptainName() + "|" +
                         t.getMatchesPlayed() + "|" + t.getWins());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving teams: " + e.getMessage());
        }
    }

    public static Queue<Team> loadTeams() {
        Queue<Team> queue = new LinkedList<>();
        File f = new File(TEAM_FILE);
        if (!f.exists()) return queue;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split("\\|", -1);
                if (t.length < 5) continue;
                int id = Integer.parseInt(t[0]);
                String name = t[1];
                String captain = t[2];
                int matches = Integer.parseInt(t[3]);
                int wins = Integer.parseInt(t[4]);

                Team team = new Team(name, captain, matches, wins);
                team.setTeamIdForLoading(id);
                queue.add(team);
            }
        } catch (IOException e) {
            System.err.println("⚠️ Error loading players: " + e.getMessage());

        }
        return queue;
    }
}

