/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import com.mafia.server.model.acts.Activity;
import com.mafia.server.model.acts.StartGameActivity;
import com.mafia.server.model.state.MafiaTypes.TEAM;
import com.mafia.server.util.ArrayListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public class Game {

    private final String key;
    private final ConcurrentHashMap<String, Player> players;

    private ArrayList<Activity> activities;
    private ArrayList<Player> thoseWhoAreAboutToDie;

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
        this.thoseWhoAreAboutToDie = new ArrayList<>();
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

        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
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
        if (activities.isEmpty()) {
            System.out.println("Empty acitivities on Game");
            return true;
        }
        for (Activity activity : activities) {
            if (!activity.isDone()) {
                return false;
            }
        }
        return true;
    }

    public void removeActivities() {
        activities.clear();
        //Remove the activity from the player
        for (Player player : getPlayersAsList()) {
            player.setActivity(null);
        }
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public ArrayList<Player> getPlayersWithRole(MafiaTypes.PLAYER_ROLES player_roles) {
        ArrayList<Player> results = new ArrayList<>();
        for (Player player : getPlayersAsList()) {
            if (player.getRole().equals(player_roles)) {
                results.add(player);
            }
        }
        return results;
    }

    public ArrayList<Player> getPlayersWithoutRole(MafiaTypes.PLAYER_ROLES player_roles) {
        ArrayList<Player> results = new ArrayList<>();
        for (Player player : getPlayersAsList()) {
            if (!player.getRole().equals(player_roles)) {
                results.add(player);
            }
        }
        return results;
    }

    public void addToChoppingBlock(Player player) {
        if (!thoseWhoAreAboutToDie.contains(player)) {
            thoseWhoAreAboutToDie.add(player);
        }
    }

    public void executeActivities() {
        if (activities == null) {
            System.err.println("Activities is null in Game");
        }
        for (Activity activity : activities) {
            activity.execute();
        }

    }

    public ArrayList<Player> getPlayersAboutToDie() {
        return thoseWhoAreAboutToDie;
    }

    public void savePlayer(Player votedPlayer) {
        thoseWhoAreAboutToDie.remove(votedPlayer);
    }

    public void clearChoppingBlock() {
        thoseWhoAreAboutToDie.clear();
    }

    public ArrayList<Player> getPlayersWhoAreDead() {
        ArrayList<Player> list = (ArrayList<Player>) getPlayersAsList();
        new ArrayListUtils<Player>().removeSome(list, new ArrayListUtils.Checker<Player>() {
            @Override
            public boolean check(Player t) {
                return t.isAlive();
            }
        });
        return list;
    }

    public ArrayList<Player> getPlayersWhoAreAlive() {
        ArrayList<Player> list = (ArrayList<Player>) getPlayersAsList();
        new ArrayListUtils<Player>().removeSome(list, new ArrayListUtils.Checker<Player>() {
            @Override
            public boolean check(Player t) {
                return !t.isAlive();
            }
        });
        return list;
    }

    public ArrayList<Player> getCivilians() {
        ArrayList<Player> results = new ArrayList<>();
        for (Player player : getPlayersAsList()) {
            if (player.getRole().getTeam().equals(TEAM.CIVILIAN)) {
                results.add(player);
            }
        }
        return results;
    }

    public ArrayList<Player> getKillers() {
        ArrayList<Player> results = new ArrayList<>();
        for (Player player : getPlayersAsList()) {
            if (player.getRole().getTeam().equals(TEAM.KILLER)) {
                results.add(player);
            }
        }
        return results;
    }

    public void removeDead(ArrayList<Player> list) {
        Iterator<Player> iterator = list.iterator();
        while (iterator.hasNext()) {
            Player next = iterator.next();
            if (!next.isAlive()) {
                iterator.remove();
            }
        }
    }

}
