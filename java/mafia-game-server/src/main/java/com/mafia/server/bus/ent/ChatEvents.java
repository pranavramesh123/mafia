/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.ent;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.ChatMessage;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class ChatEvents {

    public static void messageEveryoneInGame(Player creator, String message) {
        Game game = creator.getGame();

        StringBuilder out = new StringBuilder();
        if (message.startsWith("\\me")) {
            out.append("<i>").append(creator.getName());
            message = message.substring(3);
            out.append(message);
            out.append("</i>");
        } else {
            out.append("> ");
            out.append("<font color='blue'>");
            out.append(creator.getName());
            out.append("</font>");
            out.append(": ");
            out.append(message);
        }
        out.append("<br />");
        ChatMessage chatMessage = new ChatMessage(out.toString());
        MessageRouter.sendMessage(game, chatMessage);

    }
}
