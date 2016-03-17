/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.bus.events.GameEvents;
import com.mafia.server.bus.notify.GameNotify;
import com.mafia.server.model.comm.client.CreateGame;
import com.mafia.server.model.state.Game;

/**
 *
 * @author Just1689
 */
public class CreateGameAction implements Runnable, Action {

    private CreateGame data;
    private String createdBy;

    @Override
    public void run() {
        //impl
        System.out.println(data.toString());
        Game game = GameEvents.create(data.getName(), data.getPassCode(), createdBy);

        GameNotify.sendGameState(game);
        GameNotify.notifyCreatorOfGameCode(createdBy, game.getKey());
    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (CreateGame) obj;
        this.createdBy = sessionId;

    }

}
