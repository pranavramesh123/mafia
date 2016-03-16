/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.comm.client.CreateGame;

/**
 *
 * @author Just1689
 */
public class VotePlayerEvent implements Runnable, Event {

    private CreateGame data;

    @Override
    public void run() {
        //impl
    }

    @Override
    public void setData(Object obj) {
        this.data = (CreateGame) obj;
    }

}
