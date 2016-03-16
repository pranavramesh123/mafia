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
        ChatMessage chatMessage = new ChatMessage(creator, message);
        MessageRouter.sendMessage(game, chatMessage);

    }
}
