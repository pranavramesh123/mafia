/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.notify;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.GameState;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class NotifyGame {

    public static void sendGameState(Game game) {
        GameState gameState = new GameState(game.getGameState().toString(), game.getKey());
        MessageRouter.sendMessage(game, gameState);

    }

    public static void notifyCreatorOfGameCode(String createdBy, String key) {
        Player player = Repository.getPlayerBySessionId(createdBy);
        Messagebox messagebox = new Messagebox();
        messagebox.setAsMessageBoxSuccess("Game Code", key);
        MessageRouter.sendMessage(player, messagebox);
    }

}
