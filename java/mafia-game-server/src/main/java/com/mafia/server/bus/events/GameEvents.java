/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.notify.GameNotify;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;
import com.mafia.server.util.StringUtils;

/**
 *
 * @author Just1689
 */
public class GameEvents {

    public static void create(String playerName, String playerPassCode, String createdBy) {
        Player player = new Player(playerName, playerPassCode, createdBy);
        Repository.addPlayer(player);
        createGame(player);
    }

    private static synchronized void createGame(Player player) {

        //Create a unique key
        String newKey = StringUtils.makeUniqueKey(5);
        while (Repository.getGameByKey(newKey) != null) {
            newKey = StringUtils.makeUniqueKey(5);
        }

        Game game = new Game(player, newKey);
        Repository.createGame(game);

        PlayerEvents.joinGame(player, game);

        GameNotify.sendGameState(game);
        GameNotify.notifyCreatorOfGameCode(player.getSessionId(), game.getKey());

    }

}
