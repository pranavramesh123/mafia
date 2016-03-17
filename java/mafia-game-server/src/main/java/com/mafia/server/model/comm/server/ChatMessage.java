/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.server;

import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class ChatMessage extends ServerMessage {

    private String player;
    private String message;

    public ChatMessage(Player creator, String message) {
        this.player = creator.getName();
        this.message = message;
    }

    public ChatMessage(String name, String message) {
        this.player = name;
        this.message = message;
    }

    /**
     * @return the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Player: " + player + ", message: " + message;
    }

}
