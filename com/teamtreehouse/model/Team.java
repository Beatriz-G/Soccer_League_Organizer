package com.teamtreehouse.model;

import java.util.Set;
import java.util.HashSet;

public class Team implements Comparable<Team>{
    private String mTeamName;
    private String mTeamCoach;
    private Set<Player> mTeamPlayer;

    public Team(String teamName, String teamCoach) {
        mTeamName = teamName;
        mTeamCoach = teamCoach;
        mTeamPlayer= new HashSet<Player>();
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getTeamCoach() {

        return mTeamCoach;
    }

    public Set<Player> getTeamPlayer() {

        return mTeamPlayer;
    }





    @Override
    public int compareTo(Team o) {
        return 0;
    }
}
