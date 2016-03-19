/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public abstract class Activity {

    //Players by sessionId
    private ConcurrentHashMap<String, Player> players;

    //Votes of Player and name of player
    private ConcurrentHashMap<Player, String> votes;

    //Settings
    private int concensusPercentage;
    private ACTIVITY_PARTICIPATION participationType;

    public abstract void vote(Player player, String vote);

    public abstract void execute();

    public Activity(int concensusPercentage, ACTIVITY_PARTICIPATION participationType, ArrayList<Player> players) {
        this.votes = new ConcurrentHashMap<>();
        this.concensusPercentage = concensusPercentage;
        this.participationType = participationType;
        this.players = new ConcurrentHashMap<>();
        if (players != null) {
            for (Player player : players) {
                this.players.put(player.getSessionId(), player);
            }
        }
    }

    public Activity(int concensusPercentage, ACTIVITY_PARTICIPATION participationType) {
        this(concensusPercentage, participationType, new ArrayList<Player>());
    }

    public Player getAPlayer() {
        for (Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            return player;
        }
        return null;
    }

    /**
     *
     * @return a specific implementations understanding of is it done
     */
    public abstract boolean isDone();

    /**
     * @return the players
     */
    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ConcurrentHashMap<String, Player> players) {
        this.players = players;
    }

    /**
     * @return the concensusPercentage
     */
    public int getConcensusPercentage() {
        return concensusPercentage;
    }

    /**
     * @param concensusPercentage the concensusPercentage to set
     */
    public void setConcensusPercentage(int concensusPercentage) {
        this.concensusPercentage = concensusPercentage;
    }

    /**
     * @return the participationType
     */
    public ACTIVITY_PARTICIPATION getParticipationType() {
        return participationType;
    }

    /**
     * @param participationType the participationType to set
     */
    public void setParticipationType(ACTIVITY_PARTICIPATION participationType) {
        this.participationType = participationType;
    }

    /**
     * @return the votes
     */
    public ConcurrentHashMap<Player, String> getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(ConcurrentHashMap<Player, String> votes) {
        this.votes = votes;
    }

    public Player getVotedPlayer() {
        Enumeration<String> elements = votes.elements();
        while (elements.hasMoreElements()) {
            String vote = elements.nextElement();
            if (vote.equals("abstain")) {
                continue;
            }
            Player player = getGame().getPlayerByName(vote);
            if (player == null) {
                System.err.println("Serious error: found null player (Activity.getVotedPlayer()");
            }
            return player;
        }
        return null;
    }

    public Game getGame() {
        Player player = getAPlayer();
        Game game = player.getGame();
        return game;
    }

}
