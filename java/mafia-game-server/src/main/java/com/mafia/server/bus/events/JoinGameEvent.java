/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.ent.PlayerEvents;
import com.mafia.server.model.comm.client.JoinGame;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class JoinGameEvent implements Runnable, Event {

    private JoinGame data;
    private String createdBy;

    @Override
    public void run() {
        //impl
        System.out.println(data.toString());

        Player player = new Player(data.getName(), data.getPassCode(), createdBy);
        Repository.addPlayer(player);

        Game game = Repository.getGameByKey(data.getKey());

        PlayerEvents.joinGame(player, game);

    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (JoinGame) obj;
        this.createdBy = sessionId;

    }

}
