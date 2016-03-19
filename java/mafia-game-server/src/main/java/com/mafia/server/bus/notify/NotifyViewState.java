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
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.NIGHT;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.ACTIVITY;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.PREGAME;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;

/**
 *
 * @author Just1689
 */
public class NotifyViewState {

    public static void nofity(Game game) {
        if (game.getGamePhase().equals(PREGAME)) {
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

        if (game.getGamePhase().equals(ACTIVITY)) {
            if (game.getActivityPhase().equals(NIGHT)) {
                ArrayList<Player> killers = game.getPlayersWithRole(MafiaTypes.PLAYER_ROLES.KILLER);
                for (Player player : killers) {
                    ViewState viewState = new ViewState();
                    viewState.setKey(game.getKey());
                    viewState.setChat(true);
                    viewState.setChatInput(true);
                    viewState.setCreateOrJoin(false);
                    viewState.setLeaveGame(true);
                    viewState.setVote(false);
                    viewState.setReady(true);
                    viewState.setInstructDiv(true);
                    viewState.setInstructMessage("<strong>NIGHT:</strong> Type \"\\vote name\" of person to kill");
                    MessageRouter.sendMessage(player, viewState);
                }
                ArrayList<Player> villagers = game.getPlayersWithoutRole(MafiaTypes.PLAYER_ROLES.KILLER);
                for (Player player : villagers) {
                    ViewState viewState = new ViewState();
                    viewState.setKey(game.getKey());
                    viewState.setChat(true);
                    viewState.setChatInput(true);
                    viewState.setCreateOrJoin(false);
                    viewState.setLeaveGame(true);
                    viewState.setVote(false);
                    viewState.setReady(true);
                    viewState.setInstructDiv(true);
                    viewState.setInstructMessage("<strong>NIGHT:</strong> You are asleep");
                    MessageRouter.sendMessage(player, viewState);
                }

            }
        }
    }
}
