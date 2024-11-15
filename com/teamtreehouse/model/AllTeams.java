package com.teamtreehouse.model;

import java.util.*;

public class AllTeams {
    private Set<Team> mTeam;

    public AllTeams() {
        mTeam = new HashSet<>();
    }

    public List<Team> getTeams() {
        List<Team> list = new ArrayList<>(mTeam);
        Collections.sort(list);
        return list;
    }

    // shows list of players
    public void addTeam(Team team) {
        mTeam.add(team);
        System.out.printf("All saved teams: %n");
        for (Team savedTeam : mTeam) {
            System.out.printf("Team name: %s -- Coach Name %s %n%n", savedTeam.getTeamName(), savedTeam.getTeamCoach());
        }
    }

    public int getPlayers() {
        return 0;
    }

    private Map<String, List<Player>> byPlayer() {
        Map<String, List<Player>> byPlayer = new TreeMap<>();
        for (Team team : mTeam) {
            List<Player> playerPlayer = byPlayer.get(Player.getPlayer());
            if(playerPlayer == null) {
                playerPlayer = new ArrayList<>();
                byPlayer.put(Player.getPlayer(), playerPlayer);
            }
            Player Player = null;
            playerPlayer.add(Player);
        }
        return byPlayer;
    }




/*
    private Map<String, List<Player>> byPlayer() {
        Map<String, List<Player>> byPlayer = new TreeMap<>();
        for (Team team : mTeam) {
            List<Player> playerPlayer = byPlayer.get(Player.getPlayer());
            if(playerPlayer == null) {
                playerPlayer = new ArrayList<>();
                byPlayer.put(Player.getPlayer(), playerPlayer);
            }
            Player Player = null;
            playerPlayer.add(Player);
        }
        return byPlayer;

    public List<Team> getPlayersforTeam(String playerName) {
        List<Player> teams = byPlayer().get(playerName);
        teams.sort(new Comparator<Team>() {

            @Override
            public int compare(Team o1, Team o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1.getTeamName().compareTo(o2.getTeamName());
            }
        });
        return teams;
    }*/
}
