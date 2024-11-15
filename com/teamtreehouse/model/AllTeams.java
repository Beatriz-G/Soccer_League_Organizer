package com.teamtreehouse.model;

import java.util.*;

public class AllTeams {
    private Set<Team> mTeams;

    public AllTeams() {
        mTeams = new HashSet<>();
    }

    public List<Team> getTeams() {
        List<Team> list = new ArrayList<>(mTeams);
        Collections.sort(list);
        return list;
    }

    // Adds a team and shows list of saved teams
    public void addTeam(Team team) {
        mTeams.add(team);
        System.out.printf("All saved teams:%n");
        for (Team savedTeam : mTeams) {
            System.out.printf("Team name: %s -- Coach Name: %s %n", savedTeam.getTeamName(), savedTeam.getTeamCoach());
        }
    }

    // Gets a mapping from player names to the teams they are in
    private Map<String, List<Team>> byPlayer() {
        Map<String, List<Team>> byPlayer = new TreeMap<>();
        for (Team team : mTeams) {
            for (Player player : team.getTeamPlayers()) {
                String playerName = player.getPlayer();
                List<Team> teamsForPlayer = byPlayer.get(playerName);
                if (teamsForPlayer == null) {
                    teamsForPlayer = new ArrayList<>();
                    byPlayer.put(playerName, teamsForPlayer);
                }
                teamsForPlayer.add(team);
            }
        }
        return byPlayer;
    }

    public Set<String> getPlayers() {
        return byPlayer().keySet();
    }

    public List<Team> getTeamsForPlayer(String playerName) {
        return byPlayer().get(playerName);
    }
}