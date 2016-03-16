/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.bus.ent.PlayerEvents;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class MessageHandler {

    public static synchronized void handleMessageFromClient(Session session, String message) {

    }

    public static synchronized void handleDisconnect(Session session) {
        PlayerEvents.playerQuits(session);
    }

    public static synchronized void sendMessage(Game game, String message) {
        Enumeration<Player> elements = game.getPlayers().elements();
        while (elements.hasMoreElements()) {
            Player player = elements.nextElement();
            sendMessage(player, message);
        }

    }

    public static synchronized void sendMessage(Player player, String message) {
        Session session = Repository.getSessionByPlayer(player);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
