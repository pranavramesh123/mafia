/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.bus.events.ChatEvents;
import com.mafia.server.model.comm.client.Chat;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class ChatAction implements Runnable, Action {

    private Chat data;
    private String createdBy;

    @Override
    public void run() {
        if (data == null || data.getMessage() == null || data.getMessage().trim().isEmpty()) {
            return;
        }
        ChatEvents.handleChatMessageFromClients(Repository.getPlayerBySessionId(createdBy), data.getMessage());
    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (Chat) obj;
        this.createdBy = sessionId;
    }

}
