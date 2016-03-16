/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.comm.client.LeaveGame;

/**
 *
 * @author Just1689
 */
public class LeaveGameEvent implements Runnable, Event {

    private LeaveGame data;

    @Override
    public void run() {
        //impl
    }

    @Override
    public void setData(Object obj) {
        this.data = (LeaveGame) obj;
    }

}
