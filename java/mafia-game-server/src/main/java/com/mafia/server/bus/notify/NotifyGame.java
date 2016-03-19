/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.notify;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.comm.server.PlayerList;
import com.mafia.server.model.state.Game;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.NIGHT;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.ACTIVITY;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.PREGAME;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class NotifyGame {

    public static void notifyCreatorOfGameCode(String createdBy, String key) {
        Player player = Repository.getPlayerBySessionId(createdBy);
        Messagebox messagebox = Messagebox.createMessageBoxSuccess("Game Code", key);
        MessageRouter.sendMessage(player, messagebox);
    }

    public static void sendPlayerList(Game game) {
        if (game.getGamePhase().equals(PREGAME)) {
            PlayerList playerList = PlayerList.makeReadyVsNot(game.getPlayersAsList());
            MessageRouter.sendMessage(game, playerList);
            return;
        }

        if (game.getGamePhase().equals(ACTIVITY)) {
            if (game.getActivityPhase().equals(NIGHT)) {
                PlayerList playerList = new PlayerList(game.getPlayersAsList());
                MessageRouter.sendMessage(game, playerList);
                return;
            }
        }

        PlayerList playerList = PlayerList.makeReadyVsNot(game.getPlayersAsList());
        MessageRouter.sendMessage(game, playerList);
    }

}
