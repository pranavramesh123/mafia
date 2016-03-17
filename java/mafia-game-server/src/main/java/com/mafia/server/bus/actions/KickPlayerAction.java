/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.bus.events.GameEvents;
import com.mafia.server.model.comm.client.KickPlayer;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class KickPlayerAction implements Runnable, Action {

    private KickPlayer data;
    private String createdBy;

    @Override
    public void run() {
        //impl
        GameEvents.kickPlayer(Repository.getPlayerBySessionId(createdBy), data.getPlayer());
    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (KickPlayer) obj;
        this.createdBy = sessionId;

    }

}
