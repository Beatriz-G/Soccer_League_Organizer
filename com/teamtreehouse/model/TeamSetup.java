package com.teamtreehouse.model;

import java.util.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamSetup {
    private static Object mTeamName;
    private static Object mTeamCoach;
    private static AllTeams mAllTeams;
    private static BufferedReader mReader;
    private static Object mAllTeam;
    private List<Player> mPlayers;
    private static Map<String, String> mMenu;

    public TeamSetup(AllTeams allTeams) {
        mAllTeams = allTeams;
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
    private static String promptAction() throws IOException {
        System.out.printf("Menu %n");
        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            System.out.printf("%s -> %s%n", option.getKey(), option.getValue());
        }
        System.out.print("Select an option:  ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    // Ask user what the new team name and coach should be
    private static Team promptNewTeam() throws IOException {
        System.out.print("What is the new team name?   ");
        String mTeamName = mReader.readLine();
        System.out.print("What is the coach name?  ");
        String mTeamCoach = mReader.readLine();
        return new Team(mTeamName, mTeamCoach);
    }

    public static void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "create":
                        Team team = promptNewTeam();
                        mAllTeams.addTeam(team);
                        System.out.printf("Team %s coached by %s has been added. %n%n", team.getTeamName(), team.getTeamCoach());
                    break;
                    case "add":
                        String player = promptPlayer();
                        String teamPlayer = promptPlayer();


                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }


    // Add an existing player
    private static String promptPlayer() throws IOException {
        System.out.println("Available Players: ");
        List<String> mPlayers = new ArrayList<>(mAllTeams.getPlayers());
        int index = promptForIndex(mPlayers);
        return mPlayers.get(index);
    }

   private Team promptPlayerforTeam(String player) throws IOException {
        List<Team> teams = mAllTeams.getTeams(player);
        List<String> playerNames = new ArrayList<>();
        for (Team team : teams) {
            playerNames.add(team.getTeamName());
        }
        System.out.printf("Available players: %s", player);
        int index = promptForIndex(playerNames);
        return teams.get(index);

        //return null;
    }

    private static int promptForIndex(List<String> players) throws IOException {
        int counter = 1;
        for (String option : players) {
            System.out.printf("%d.) %s %n", counter, option);
            counter++;
        }
        System.out.printf("Your pick:  ");
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }
}