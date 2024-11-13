package com.teamtreehouse.model;

import java.util.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TeamSetup {
    private AllTeams mAllTeams;
    private static BufferedReader mReader;
    private static Map<String, String> mMenu;
    private List<Player> mPlayers;


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
        System.out.printf("Menu");
        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            System.out.printf("%s - %s%n", option.getKey(), option.getValue());
        }
        System.out.print("Select an option: ");
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
                        AllTeams.allTeams(team);
                        System.out.printf("Team %s coached by %s has been added.", mTeamName, mTeamCoach);
                }

            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }
}