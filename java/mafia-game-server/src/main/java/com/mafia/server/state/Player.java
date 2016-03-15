/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.state;

import javax.websocket.Session;

/**
 *
 * @author w1428368
 */
public class Player {

    private String name;
    private MafiaTypes.PLAYER_ROLES role;
    private Session session;

    public Player(String name, Session session) {
        this.name = name;
        this.role = MafiaTypes.PLAYER_ROLES.NONE;
        this.session = session;
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
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }
}
