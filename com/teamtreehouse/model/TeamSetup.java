package com.teamtreehouse.model;

import java.util.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;


public class TeamSetup {
    private  AllTeams mAllTeams;
    private  BufferedReader mReader;
    private List<Player> mPlayers;
    private  Map<String, String> mMenu;

    public TeamSetup() {
        mAllTeams = new AllTeams();
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mPlayers = new ArrayList<>(Arrays.asList(Players.load()));
        mMenu = new HashMap<>();
        mMenu.put("Create", "Create a new team");
        mMenu.put("Add", "Add a player to a team");
        mMenu.put("Remove", "Remove a player from a team");
        mMenu.put("Report", "View a report of a team by height");
        mMenu.put("Balance", "View the League Balance Report");
        mMenu.put("Quit", "Exits the program");
    }


    // Ask user what they want to do
    private  String promptAction() throws IOException {
        out.printf("Menu %n");
        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            out.printf("%s -> %s%n", option.getKey(), option.getValue());
        }
        out.print("Select an option:  ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    // Ask user what the new team name and coach should be
    private Team promptNewTeam() throws IOException {
        out.print("What is the new team name?   ");
        String mTeamName = mReader.readLine();
        out.print("What is the coach name?  ");
        String mTeamCoach = mReader.readLine();
        return new Team(mTeamName, mTeamCoach);
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
                        out.printf("Team %s coached by %s has been added. %n%n", team.getTeamName(), team.getTeamCoach());
                        break;
                    case "add":
                        //promptPlayer();
                        showPlayers();

                }
            } catch (IOException ioe) {
                out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }

    // Add exisiting player
    private void showPlayers() {
        int i = 1;
        for (Player player : mPlayers) {
            System.out.printf("%d). first name: %s last name: %s  height in inches: %d previous experience: %s %n",
                    i,
                    player.getFirstName(),
                    player.getLastName(),
                    player.getHeightInInches(),
                    player.isPreviousExperience());

            i++;
        }
    }

    private Team promptPlayerforTeam(Player player) throws IOException {
        List<Team> teams = mAllTeams.getTeams();
        List<Player> playerNames = new ArrayList<>();
//        for (Team team : teams) {
//            playerNames.add(team.getTeamName());
//        }
        out.printf("Available players: %s", player);
        int index = promptForIndex(playerNames);
        return teams.get(index);
    }

    private int promptForIndex(List<Player> players) throws IOException {
        int counter = 1;
        for (Player option : players) {
            out.printf("%d.) %s %n", counter, option);
            counter++;
        }
        out.printf("Your pick:  ");
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }
}