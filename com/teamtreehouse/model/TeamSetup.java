package com.teamtreehouse.model;

import java.util.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

import static java.lang.System.out;


public class TeamSetup {
    private  AllTeams mAllTeams;
    private  BufferedReader mReader;
    private List<Player> mPlayers;
    private  Map<String, String> mMenu;
    private Queue mPlayerQueue;

    public TeamSetup() {
        mAllTeams = new AllTeams();
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mPlayers = new ArrayList<>(Arrays.asList(Players.load()));
        mPlayerQueue = new ArrayDeque<Player>());
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
            System.out.printf("%s: %s%n",
                    option.getKey(), option.getValue());

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
                        showPlayers();
                        Team player = promptPlayerforTeam(player);
                        mPlayerQueue.add(player);
                        out.printf("You chose:   %s %n", player);
                        if (int teamPlayer >= 11) {
                            out.printf("Team has reached maximum number of players.");
                        }
                        break;
                    case "remove":
                        Team team = promptNewTeam();
                        removePlayer();
                        out.printf("Player %s has been removed.", );
                        break;
                    case "report":
                        Team team = promptNewTeam();
                        heightReport();
                        break;
                    case "balance":
                        Team team = promptNewTeam();
                        balanceReport();
                        break;
                    case "roster":
                        Team team = promptNewTeam();
                        roster();
                        break;
                    case "quit":
                        out.println("End of Program");
                    break;
                    default:
                        out.printf("Unknown choice:  '%s'. Try again.  %n%n%n",
                                choice);
                }
            } catch (IOException ioe) {
                out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }

    // Add existing player
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
    // Part of add
    private Team promptPlayerforTeam(Player player) throws IOException {
        List<Team> teams = mAllTeams.getTeams();
        List<String> playerNames = new ArrayList<>();
//        for (Team team : teams) {
//            playerNames.add(team.getTeamName());
//        }
        out.printf("Available players: %s", player);
        int index = promptForIndex(playerNames);
        return teams.get(index);
    }

    // Part of add
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

    // Part of remove

     public Player promptRemovePlayer() throws IOException() {
            List<Players> players = mAllTeams.getPlayers();

            out.println("Chose a player for removal: %n");
            String  = mReader.readLine();
            int newPlayer = Integer.parseInt(newPlayerAsString.trim());
            newPlayer = newPlayer - 1;

            return new ArrayList<>(player).get(newPlayer);
     }

        // report
     public void heightReport(Team) throws IOException {
            Team team = promptNewTeam();
            List<Players> players = mAllTeams.getPlayers();
            List<Player> heightOne = new ArrayList<>();
            List<Player> heightTwo = new ArrayList<>();
            List<Player> heightThree = new ArrayList<>();

            for (player player : players) {
            int height = player.getHeightInInches();
            if (height >= 35 && height <= 40) {
                heightOne.add(player);
            } else if (height >= 41 && height <= 46) {
                heightOne.add(player);
            } (height >= 47 && height <= 51) {
                    heightOne.add(player);
            }
        }
         out.println("Height report for players: " + promptNewTeam.getTeamName());
            for (Player player : heightOne)  {
                out.printf("Thre are %d players in the range of 35-40 inches", heightOne.size());
                out.printf("Name: %s %s, Height in inches: %s, Experience: %s %n");
                        player.getFirstName(),
                        player.getLastName(),
                        player.getHeightInInches(),
                        player.isPreviousExperience());
            }
            out.println("Height report for players: " + promptNewTeam.getTeamName());
            for (Player player : heightTwo)  {
                out.printf("Thre are %d players in the range of 41-46 inches", heightTwo.size());
                out.printf("Name: %s %s, Height in inches: %s, Experience: %s %n");
                player.getFirstName(),
                        player.getLastName(),
                        player.getHeightInInches(),
                        player.isPreviousExperience());
            }
            out.println("Height report for players: " + promptNewTeam.getTeamName());
            for (Player player : heightThree)  {
                out.printf("Thre are %d players in the range of 47-51 inches", heightThree.size());
                out.printf("Name: %s %s, Height in inches: %s, Experience: %s %n");
                player.getFirstName(),
                        player.getLastName(),
                        player.getHeightInInches(),
                        player.isPreviousExperience());
            }
    }

        //balance report
     private void balanceReport(Team team) {
         List<Players> players = mAllTeams.getPlayers();
         Map<String, int[]> experienceCountMap = new HashMap<>();
         for(Team team : team);
         int experiencedPlayer = 0;
         int inexperiencedPlayer = 0;
         for(Player player : player);
         if (players.isPreviousExperience()==true) {
             expereincedPlayer++;


         }



    }

    private void roster() throws IOException(Team mteam) {
         List<Players> players = mAllTeams.getPlayers();
            List<Players> listOfPlayers = mAllTeams.getPlayers();
         for (Team team : mAllTeams.getTeams()) {
             int i = 1;
             out.printf();"Team %s coached by %s - ", team.getTeamName(), team.getTeamCoach();
             for (Player player : listOfPlayers) {
                 out.printf("%d). first name: %s, last name: %s, Height in Inches: %d, Previous Experience: %d",
                         i,
                         player.getFirstName(),
                         player.getLastName(),
                         player.getHeightInInches(),
                         player.isPreviousExperience());
                 i++;

             }
    }


}





