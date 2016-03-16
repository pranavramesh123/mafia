/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class PlayerEvents {

    public static void playerQuits(Session session) {
        Player player = Repository.getPlayerBySession(session);
        player.getGame().removePlayer(player);

        //Todo: notify?
    }

    public static void joinGame(Player player, Game game) {
        player.setGame(game);
    }

}
