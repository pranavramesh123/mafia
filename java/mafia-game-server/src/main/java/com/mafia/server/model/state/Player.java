/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

/**
 *
 * @author Just1689
 */
public class Player {

    private String name;
    private String passKey;
    private MafiaTypes.PLAYER_ROLES role;
    private String sessionId;
    private Game game;

    public Player(String name, String sessionId) {
        this.name = name;
        this.role = MafiaTypes.PLAYER_ROLES.NONE;
        this.sessionId = sessionId;
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
}
