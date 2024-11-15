package com.teamtreehouse.model;

import java.util.Set;
import java.util.HashSet;

public class Team implements Comparable<Team> {
    private String mTeamName;
    private String mTeamCoach;
    private Set<Player> mTeamPlayers;

    public Team(String teamName, String teamCoach) {
        mTeamName = teamName;
        mTeamCoach = teamCoach;
        mTeamPlayers = new HashSet<>();
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getTeamCoach() {
        return mTeamCoach;
    }

    public Set<Player> getTeamPlayers() {
        return mTeamPlayers;
    }

    public void addPlayer(Player player) {
        mTeamPlayers.add(player);
    }

    public void removePlayer(Player player) {
        mTeamPlayers.remove(player);
    }

    @Override
    public int compareTo(Team other) {
        // Implemented comparison based on team name
        return this.mTeamName.compareTo(other.mTeamName);
    }
}