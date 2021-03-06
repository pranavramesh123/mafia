/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

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
public class GameWebsocket {

    @OnOpen
    public void onOpen(Session session) {
        MessageHandler.handleConnect(session);
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
