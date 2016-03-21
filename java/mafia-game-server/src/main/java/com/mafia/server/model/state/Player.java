/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import com.mafia.server.model.acts.Activity;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Just1689
 */
public class Player {
    public static Comparator<Player> NAME_COMPARATOR = new Comparator<Player>() {
        public int compare(Player o1, Player o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static String getPlayersNamesString(ArrayList<Player> list) {
        StringBuilder out = new StringBuilder();
        for (Player player : list) {
            if (out.length() > 0) {
                out.append(", ");
            }
            out.append(player.getName());
        }
        return out.toString();
    }

    private String name;
    private String passKey;
    private MafiaTypes.PLAYER_ROLES role;
    private String sessionId;
    private Game game;
    private Activity activity;
    private boolean alive;

    public Player(String name, String passCode, String sessionId) {
        this.name = name;
        this.passKey = passCode;
        this.role = MafiaTypes.PLAYER_ROLES.NONE;
        this.sessionId = sessionId;
        this.alive = true;
    }

    public boolean hasVoted() {
        if (activity == null) {
            return false;
        }
        String vote = activity.getVotes().get(this);
        return vote != null;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the role
     */
    public MafiaTypes.PLAYER_ROLES getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(MafiaTypes.PLAYER_ROLES role) {
        this.role = role;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the passKey
     */
    public String getPassKey() {
        return passKey;
    }

    /**
     * @param passKey the passKey to set
     */
    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object obj) {
        return getSessionId().equals(((Player) obj).sessionId);
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }


}
