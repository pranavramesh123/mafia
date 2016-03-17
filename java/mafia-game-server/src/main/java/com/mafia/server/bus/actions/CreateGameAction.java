/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.bus.events.GameEvents;
import com.mafia.server.bus.events.MessageboxEvents;
import com.mafia.server.model.comm.client.CreateGame;

/**
 *
 * @author Just1689
 */
public class CreateGameAction implements Runnable, Action {

    private CreateGame data;
    private String createdBy;

    @Override
    public void run() {

        if (data.getName() == null || data.getName().trim().isEmpty()) {
            MessageboxEvents.notifyOfFail("Missing info", "Please enter a name", createdBy);
            return;
        }
        if (data.getPassCode() == null || data.getPassCode().trim().isEmpty()) {
            MessageboxEvents.notifyOfFail("Missing info", "Please enter a passcode", createdBy);
            return;
        }
        GameEvents.create(data.getName(), data.getPassCode(), createdBy);

    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (CreateGame) obj;
        this.createdBy = sessionId;

    }

}
