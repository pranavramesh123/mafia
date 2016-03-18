/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.bus.notify.NotifyViewState;
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
        if (player == null) {
            return;
        }
        Game game = player.getGame();
        if (game == null) {
            return;
        }

        game.removePlayer(player);
        GameEvents.checkGame(game);
        NotifyViewState.nofity(game);
        Repository.removePlayer(player);
        player.setGame(null);
        NotifyGame.sendPlayerList(game);

    }

    public static Player makePlayer(String name, String passCode, String sessionId) {
        return new Player(name, passCode, sessionId);
    }

    public static void joinGame(Player player, Game game) {
        player.setGame(game);
        game.addPlayer(player);
        NotifyViewState.nofity(game);
    }

}
