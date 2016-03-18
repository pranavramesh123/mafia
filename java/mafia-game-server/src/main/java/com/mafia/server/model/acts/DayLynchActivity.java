/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class DayLynchActivity extends Activity {

    public DayLynchActivity() {
        super(100, MafiaTypes.ACTIVITY_PARTICIPATION.GROUP);
    }

    @Override
    public void vote(Player player, String vote) {
        Game game = player.getGame();
        if (game == null) {
            System.out.println("No game found (DayLynchActivity.vote)");
            return;
        }

        if (game.getGameState().equals(MafiaTypes.GAME_PHASE.PREGAME)) {
            //They are voting to start the game
            return;
        }
        if (game.getGameState().equals(MafiaTypes.GAME_PHASE.POSTGAME)) {
            Messagebox messagebox = Messagebox.createMessageBoxError("Error", "Cannot vote after game");
            MessageRouter.sendMessage(player, messagebox);
            return;
        }

    }

}
