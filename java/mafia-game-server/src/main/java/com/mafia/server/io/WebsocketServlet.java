/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.bus.events.PlayerEvents;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Just1689
 */
@ServerEndpoint("/ws")
public class WebsocketServlet {

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        MessageHandler.handleMessageFromClient(session, message);
    }

    @OnClose
    public void onClose(Session session) {
        MessageHandler.handleDisconnect(session);
    }
}
