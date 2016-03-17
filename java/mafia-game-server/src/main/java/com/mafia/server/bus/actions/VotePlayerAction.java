/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.model.comm.client.CreateGame;

/**
 *
 * @author Just1689
 */
public class VotePlayerAction implements Runnable, Action {

    private CreateGame data;
    private String createdBy;

    @Override
    public void run() {
    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (CreateGame) obj;
        this.createdBy = sessionId;

    }

}
