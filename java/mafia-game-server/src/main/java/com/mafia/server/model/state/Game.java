/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public class Game {

    private final String key;
    private final ConcurrentHashMap<String, Player> players;

    private Player creator;

    private MafiaTypes.GAME_PHASE gameState;
    private MafiaTypes.ACTION_PHASE phase;

    public Game(Player creator, String key) {
        this.players = new ConcurrentHashMap<>();
        this.players.put(creator.getSessionId(), creator);
        this.gameState = MafiaTypes.GAME_PHASE.PREGAME;
        this.phase = MafiaTypes.ACTION_PHASE.NONE;
        this.key = key;
        this.creator = creator;
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
    public MafiaTypes.GAME_PHASE getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(MafiaTypes.GAME_PHASE gameState) {
        this.gameState = gameState;
    }

    /**
     * @return the phase
     */
    public MafiaTypes.ACTION_PHASE getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(MafiaTypes.ACTION_PHASE phase) {
        this.phase = phase;
    }

    public void removePlayer(Player player) {
        if (player == null) {
            return;
        }
        players.remove(player.getSessionId());
    }

    public void addPlayer(Player player) {
        players.put(player.getSessionId(), player);
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

}
