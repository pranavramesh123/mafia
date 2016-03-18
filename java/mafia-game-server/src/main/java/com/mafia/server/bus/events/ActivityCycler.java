/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes;

/**
 *
 * @author Just1689
 */
public class ActivityCycler {

    public static void checkGame(Game game) {
        if (game.isActivityComplete()) {
            System.out.println("Activity is complete");
            moveGameToNextState(game);
        } else {
            System.out.println("Activity is not complete");
        }
    }

    private static void moveGameToNextState(Game game) {
        if (game.getGamePhase().equals(MafiaTypes.GAME_PHASE.PREGAME)) {
            moveGameToPhase(game);
            return;
        }

        if (game.getActivityPhase().equals(MafiaTypes.ACTIVITY_PHASE.DAWN)) {
            moveGameToActivity(game, MafiaTypes.ACTIVITY_PHASE.DAY);
            return;
        }

    }

    private static void moveGameToPhase(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void moveGameToActivity(Game game, MafiaTypes.ACTIVITY_PHASE activity_phase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
