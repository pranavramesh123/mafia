/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.notify;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.ViewState;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes;

/**
 *
 * @author Just1689
 */
public class NotifyViewState {

    public static void nofity(Game game) {
        if (game.getGamePhase().equals(MafiaTypes.GAME_PHASE.PREGAME)) {
            ViewState viewState = new ViewState();
            viewState.setKey(game.getKey());
            viewState.setChat(true);
            viewState.setChatInput(true);
            viewState.setCreateOrJoin(false);
            viewState.setLeaveGame(true);
            viewState.setVote(false);
            viewState.setReady(true);
            viewState.setInstructDiv(true);
            viewState.setInstructMessage("<strong>PREGAME:</strong> Type \"\\vote ready\" when ready to begin game");
            MessageRouter.sendMessage(game, viewState);
        }
    }
}
