/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import com.mafia.server.model.acts.Activity;
import com.mafia.server.model.acts.StartGameActivity;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public class Game {

    private final String key;
    private final ConcurrentHashMap<String, Player> players;

    private ArrayList<Activity> activities;

    private Player creator;

    private MafiaTypes.GAME_PHASE gamePhase;
    private MafiaTypes.ACTIVITY_PHASE activityPhase;

    public Game(Player creator, String key) {
        this.players = new ConcurrentHashMap<>();
        this.players.put(creator.getSessionId(), creator);
        this.gamePhase = MafiaTypes.GAME_PHASE.PREGAME;
        this.activityPhase = MafiaTypes.ACTIVITY_PHASE.NONE;
        this.key = key;
        this.creator = creator;
        this.activities = new ArrayList<>();
        this.activities.add(new StartGameActivity(players.values()));
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the players
     */
    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }

    /**
     * @return the gameState
     */
    public MafiaTypes.GAME_PHASE getGamePhase() {
        return gamePhase;
    }

    /**
     * @param gamePhase the gameState to set
     */
    public void setGamePhase(MafiaTypes.GAME_PHASE gamePhase) {
        this.gamePhase = gamePhase;
    }

    /**
     * @return the phase
     */
    public MafiaTypes.ACTIVITY_PHASE getActivityPhase() {
        return activityPhase;
    }

    /**
     * @param activityPhase the phase to set
     */
    public void setActivityPhase(MafiaTypes.ACTIVITY_PHASE activityPhase) {
        this.activityPhase = activityPhase;
    }

    public void removePlayer(Player player) {
        if (player == null) {
            return;
        }
        players.remove(player.getSessionId());
    }

    public void addPlayer(Player player) {
        players.put(player.getSessionId(), player);
        if (gamePhase.equals(MafiaTypes.GAME_PHASE.PREGAME)) {
            StartGameActivity activity = (StartGameActivity) activities.get(0);
            activity.addPlayer(player);
            player.setActivity(activity);
        }
    }

    public Player getPlayerByName(String playerName) {
        playerName = playerName.toUpperCase();
        Enumeration<Player> elements = players.elements();
        while (elements.hasMoreElements()) {
            Player player = elements.nextElement();
            if (player.getName().toUpperCase().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * @return the creator
     */
    public Player getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public boolean isAbandoned() {
        return players.isEmpty();
    }

    public List<Player> getPlayersAsList() {
        return new ArrayList<>(players.values());
    }

    public boolean isActivityComplete() {
        for (Activity activity : activities) {
            if (!activity.isDone()) {
                return false;
            }
        }
        return true;
    }

    public void removeActivities() {
        activities.clear();
    }

}
