/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;
import com.mafia.server.util.StringUtils;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class GameEvents {

    public static synchronized Game create(Player player, Session session) {
        String newKey = StringUtils.makeUniqueKey(5);
        Game game = new Game(session, player, newKey);
        Repository.games.put(newKey, game);
        PlayerEvents.joinGame(player, game);
        return game;
    }

}
