/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class Repository {

    //Games stored by their keys
    private static final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    //Sessions by ID
    private static final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    //Player by ID
    private static final ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    public static Game getGameByKey(String key) {
        return games.get(key);
    }

    public static Player getPlayerBySession(Session session) {
        return getPlayerBySessionId(session.getId());
    }

    public static Player getPlayerBySessionId(String id) {
        return players.get(id);
    }

    public static Session getSessionByPlayer(Player player) {
        return sessions.get(player.getSessionId());
    }

    public static void createGame(Game game) {
        games.put(game.getKey(), game);
        reportGameCount();
    }

    public static void addPlayer(Player player) {
        players.put(player.getSessionId(), player);
    }

    public static void addSession(Session session) {
        sessions.put(session.getId(), session);
    }

    public static void removeSession(Session session) {
        sessions.remove(session.getId());
    }

    public static void removeGame(Game game) {
        games.remove(game.getKey());
        System.out.println("Removed game.");
        reportGameCount();
    }

    private static void reportGameCount() {
        System.out.println("Game count: " + games.size());

    }

}
