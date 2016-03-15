/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.state;

import com.mafia.server.utils.ConcurrentArrayList;
import java.util.ArrayList;

/**
 *
 * @author Just1689
 */
public class Game {

    private String key;
    private ConcurrentArrayList<Player> players;

    private MafiaTypes.GAME_PHASE gameState;
    private MafiaTypes.ACTION_PHASE phase;

    public Game(Player creator, String key) {
        players = new ConcurrentArrayList<>();
        players.add(creator);
        gameState = MafiaTypes.GAME_PHASE.PREGAME;
        phase = MafiaTypes.ACTION_PHASE.NONE;
        this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the players
     */
    public ConcurrentArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ConcurrentArrayList<Player> players) {
        this.players = players;
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

}
