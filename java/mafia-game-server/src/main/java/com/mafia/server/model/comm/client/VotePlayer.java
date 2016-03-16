/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.client;

import com.mafia.server.util.JacksonUtils;

/**
 *
 * @author Just1689
 */
public class VotePlayer {

    private String player;

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

    @Override
    public String toString() {
        return JacksonUtils.objectToString(this);
    }

}
