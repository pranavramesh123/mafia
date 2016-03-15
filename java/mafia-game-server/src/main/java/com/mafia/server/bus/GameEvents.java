/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus;

import com.mafia.server.state.Game;
import com.mafia.server.state.Player;
import com.mafia.server.state.Repository;
import java.util.UUID;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class GameEvents {

    public static synchronized Game create(Player player, Session session) {
        String newKey = makeUniqueKey();
        Game game = new Game(session, player, newKey);
        Repository.games.put(newKey, game);
        PlayerEvents.joinGame(player, game);
        return game;
    }

    private static String makeUniqueKey() {
        boolean ok = false;
        String key = null;
        while (!ok) {
            key = UUID.randomUUID().toString().toUpperCase().substring(1, 5);
            ok = !Repository.games.contains(key);
        }
        return key;
    }

}
