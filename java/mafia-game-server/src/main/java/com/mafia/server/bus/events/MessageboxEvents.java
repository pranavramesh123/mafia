/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class MessageboxEvents {

    public static void showMessageboxOk(Game game, String message) {
        Messagebox messagebox = new Messagebox();
        messagebox.setAsMessageBoxOk(message);
        MessageRouter.sendMessage(game, messagebox);
    }

    public static void showMessageboxTimed(Game game, String title, String message) {
        Messagebox messagebox = new Messagebox();
        messagebox.setAsMessageBoxTimed(title, message);
        MessageRouter.sendMessage(game, messagebox);
    }

    public static void notifyOfFail(String title, String message, String sessionId) {
        Messagebox messagebox = new Messagebox();
        messagebox.setAsMessageBoxError(title, message);
        Player player = new Player("", "", sessionId);
        MessageRouter.sendMessage(player, messagebox);

    }

}
