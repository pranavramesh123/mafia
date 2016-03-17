/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.bus.events.MessageboxEvents;
import com.mafia.server.bus.events.PlayerEvents;
import com.mafia.server.model.comm.client.JoinGame;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class JoinGameAction implements Runnable, Action {

    private JoinGame data;
    private String createdBy;

    @Override
    public void run() {
        //impl
        System.out.println(data.toString());

        if (data.getName() == null || data.getName().trim().isEmpty()) {
            //Say something
            return;
        }

        Game game = Repository.getGameByKey(data.getKey());
        if (game == null) {
            //Say something
            return;
        }

        Player player = new Player(data.getName(), data.getPassCode(), createdBy);
        Repository.addPlayer(player);

        MessageboxEvents.showMessageboxTimed(game, "New Player", player.getName() + " has joined");

        PlayerEvents.joinGame(player, game);

    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (JoinGame) obj;
        this.createdBy = sessionId;

    }

}
