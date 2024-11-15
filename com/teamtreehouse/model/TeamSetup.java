
package com.teamtreehouse.model;

import java.util.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class TeamSetup {
    private AllTeams mAllTeams;
    private BufferedReader mReader;
    private List<Player> mPlayers;
    private Map<String, String> mMenu;

    public TeamSetup() {
        mAllTeams = new AllTeams();
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mPlayers = new ArrayList<>(Arrays.asList(Players.load()));
        mMenu = new HashMap<>();
        mMenu.put("create", "Create a new team");
        mMenu.put("add", "Add a player to a team");
        mMenu.put("remove", "Remove a player from a team");
        mMenu.put("report", "View a report of a team by height");
        mMenu.put("balance", "View the League Balance Report");
        mMenu.put("roster", "View team rosters");
        mMenu.put("quit", "Exits the program");
    }

    private String promptAction() throws IOException {
        out.printf("Menu:%n");
        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            out.printf("%s -> %s%n", option.getKey(), option.getValue());
        }
        out.print("Select an option:  ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    private Team promptNewTeam() throws IOException {
        out.print("What is the new team name?   ");
        String teamName = mReader.readLine();
        out.print("What is the coach name?  ");
        String teamCoach = mReader.readLine();
        return new Team(teamName, teamCoach);
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "create":
                        Team team = promptNewTeam();
                        mAllTeams.addTeam(team);
                        out.printf("Team %s coached by %s has been added.%n%n", team.getTeamName(), team.getTeamCoach());
                        break;
                    case "add":
                        addPlayerToTeam();
                        break;
                    case "remove":
                        removePlayerFromTeam();
                        break;
                    case "report":
                        showHeightReport();
                        break;
                    case "balance":
                        showBalanceReport();
                        break;
                    case "roster":
                        showRoster();
                        break;
                    case "quit":
                        out.println("End of Program");
                        break;
                    default:
                        out.printf("Unknown choice: '%s'. Try again.%n%n%n", choice);
                }
            } catch (IOException ioe) {
                out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }

    private void addPlayerToTeam() throws IOException {
        List<Team> teams = mAllTeams.getTeams();
        if (teams.isEmpty()) {
            out.println("No teams available. Please create a team first.");
            return;
        }
        out.println("Select a team to add a player to:");
        int teamIndex = promptForTeamIndex(teams);
        Team team = teams.get(teamIndex);

        if (team.getTeamPlayers().size() >= 11) {
            out.println("Team has reached the maximum number of players (11).");
            return;
        }

        List<Player> availablePlayers = new ArrayList<>(mPlayers);
        if (availablePlayers.isEmpty()) {
            out.println("No available players to add.");
            return;
        }
        out.println("Select a player to add to the team:");
        int playerIndex = promptForPlayerIndex(availablePlayers);
        Player player = availablePlayers.get(playerIndex);

        team.addPlayer(player);
        mPlayers.remove(player); // Remove player from available players
        out.printf("Player %s %s has been added to team %s.%n",
                player.getFirstName(), player.getLastName(), team.getTeamName());
    }

    private void removePlayerFromTeam() throws IOException {
        List<Team> teams = mAllTeams.getTeams();
        if (teams.isEmpty()) {
            out.println("No teams available. Please create a team first.");
            return;
        }
        out.println("Select a team to remove a player from:");
        int teamIndex = promptForTeamIndex(teams);
        Team team = teams.get(teamIndex);

        Set<Player> teamPlayers = team.getTeamPlayers();
        if (teamPlayers.isEmpty()) {
            out.println("No players in this team.");
            return;
        }
        List<Player> playersList = new ArrayList<>(teamPlayers);
        out.println("Select a player to remove from the team:");
        int playerIndex = promptForPlayerIndex(playersList);
        Player player = playersList.get(playerIndex);

        team.removePlayer(player);
        mPlayers.add(player); // Add player back to available players
        out.printf("Player %s %s has been removed from team %s.%n", player.getFirstName(), player.getLastName(), team.getTeamName());
    }

    private void showHeightReport() throws IOException {
        // Show list of teams
        List<Team> teams = mAllTeams.getTeams();
        if (teams.isEmpty()) {
            out.println("No teams available. Please create a team first.");
            return;
        }
        out.println("Select a team to view the height report:");
        int teamIndex = promptForTeamIndex(teams);
        Team team = teams.get(teamIndex);

        List<Player> players = new ArrayList<>(team.getTeamPlayers());
        if (players.isEmpty()) {
            out.println("No players in this team.");
            return;
        }

        Map<String, List<Player>> heightGroups = new TreeMap<>();
        for (Player player : players) {
            String heightCategory;
            int height = player.getHeightInInches();
            if (height >= 35 && height <= 40) {
                heightCategory = "35-40 inches";
            } else if (height >= 41 && height <= 46) {
                heightCategory = "41-46 inches";
            } else {
                heightCategory = "47-50 inches";
            }
            List<Player> group = heightGroups.getOrDefault(heightCategory, new ArrayList<>());
            group.add(player);
            heightGroups.put(heightCategory, group);
        }

        out.printf("Height report for team %s:%n", team.getTeamName());
        for (Map.Entry<String, List<Player>> entry : heightGroups.entrySet()) {
            out.printf("Height group %s: %d players%n", entry.getKey(), entry.getValue().size());
            for (Player player : entry.getValue()) {
                out.printf("- %s %s, Height: %d inches, Experience: %b%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), player.isPreviousExperience());
            }
        }
    }

    private void showBalanceReport() {
        List<Team> teams = mAllTeams.getTeams();
        if (teams.isEmpty()) {
            out.println("No teams available.");
            return;
        }
        out.println("League Balance Report:");
        for (Team team : teams) {
            int experiencedPlayers = 0;
            int inexperiencedPlayers = 0;
            for (Player player : team.getTeamPlayers()) {
                if (player.isPreviousExperience()) {
                    experiencedPlayers++;
                } else {
                    inexperiencedPlayers++;
                }
            }
            out.printf("Team %s coached by %s:%n", team.getTeamName(), team.getTeamCoach());
            out.printf("Experienced players: %d%n", experiencedPlayers);
            out.printf("Inexperienced players: %d%n", inexperiencedPlayers);
            out.printf("Total players: %d%n%n", team.getTeamPlayers().size());
        }
    }

    private void showRoster() {
        List<Team> teams = mAllTeams.getTeams();
        if (teams.isEmpty()) {
            out.println("No teams available.");
            return;
        }
        for (Team team : teams) {
            out.printf("Team %s coached by %s:%n", team.getTeamName(), team.getTeamCoach());
            List<Player> players = new ArrayList<>(team.getTeamPlayers());
            Collections.sort(players);
            for (Player player : players) {
                out.printf("- %s %s, Height: %d inches, Experience: %b%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), player.isPreviousExperience());
            }
            out.println();
        }
    }

    private int promptForTeamIndex(List<Team> teams) throws IOException {
        int index = 1;
        for (Team team : teams) {
            out.printf("%d) Team: %s, Coach: %s%n", index, team.getTeamName(), team.getTeamCoach());
            index++;
        }
        out.print("Your choice: ");
        String input = mReader.readLine();
        int choice = Integer.parseInt(input.trim()) - 1;
        if (choice < 0 || choice >= teams.size()) {
            out.println("Invalid choice.");
            return promptForTeamIndex(teams);
        }
        return choice;
    }

    private int promptForPlayerIndex(List<Player> players) throws IOException {
        int index = 1;
        for (Player player : players) {
            out.printf("%d) %s %s, Height: %d inches, Experience: %b%n", index, player.getFirstName(), player.getLastName(), player.getHeightInInches(), player.isPreviousExperience());
            index++;
        }
        out.print("Your choice: ");
        String input = mReader.readLine();
        int choice = Integer.parseInt(input.trim()) - 1;
        if (choice < 0 || choice >= players.size()) {
            out.println("Invalid choice.");
            return promptForPlayerIndex(players);
        }
        return choice;
    }
}