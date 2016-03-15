/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.state;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class Repository {

    //Games stored by their keys
    public static ConcurrentHashMap<String, Game> games;

    //Sessions by ID
    public static ConcurrentHashMap<String, Session> sessions;

    public Repository() {
        games = new ConcurrentHashMap<>();
    }

    public static synchronized Game getGameByKey(String key) {
        Set<String> keys = games.keySet();
        Game result = null;
        for (String rowKey : keys) {
            Game game = games.get(rowKey);
            if (game != null) {
                if (game.getKey().equals(key)) {
                    result = game;
                    break;
                }
            }
        }
        return result;
    }

    public static synchronized Game getGameBySession(Session session) {
        Set<String> keys = games.keySet();
        Game result = null;
        for (String rowKey : keys) {
            Game game = games.get(rowKey);
            if (game != null) {
                if (game.getPlayers().get(session.getId()) != null) {
                    result = game;
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

}
