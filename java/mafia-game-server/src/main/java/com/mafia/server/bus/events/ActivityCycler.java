/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.model.acts.NightInvestigateActivity;
import com.mafia.server.model.acts.NightMurderActivity;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.DAWN;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.DAY;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.NIGHT;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.ACTIVITY;
import static com.mafia.server.model.state.MafiaTypes.GAME_PHASE.PREGAME;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.INVESTIGATOR;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.KILLER;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;
import java.util.List;

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
        if (game.getGamePhase().equals(PREGAME)) {
            moveGameToPhase(game, ACTIVITY);
            return;
        }

        if (game.getActivityPhase().equals(DAWN)) {
            moveGameToActivity(game, DAY);
            return;
        }

    }

    private static void moveGameToPhase(Game game, MafiaTypes.GAME_PHASE phase) {
        //Set the new phase
        game.setGamePhase(phase);

        //Conform state to that phase
        if (phase.equals(ACTIVITY)) {
            moveGameToActivity(game, NIGHT);
            return;
        }
    }

    private static void moveGameToActivity(Game game, MafiaTypes.ACTIVITY_PHASE activityPhase) {
        //Remove the previous days activities
        game.removeActivities();

        switch (activityPhase) {
            case NONE:

                break;
            case NIGHT:
                moveGameToActivityNight(game);
                break;
            case DAWN:

                break;
            case DAY:

                break;
            default:

                break;

        }

    }

    private static void moveGameToActivityNight(Game game) {
        game.setActivityPhase(NIGHT);

        //Handle Killers
        ArrayList<Player> killerPlayers = game.getPlayersWithRole(KILLER);
        NightMurderActivity nightMurderActivity = new NightMurderActivity(killerPlayers, true);
        game.addActivity(nightMurderActivity);

        //Handle investigators
        ArrayList<Player> investigatorPlayers = game.getPlayersWithRole(INVESTIGATOR);
        for (Player player : investigatorPlayers) {
            NightInvestigateActivity nightInvestigateActivity = new NightInvestigateActivity(player, true);
            game.addActivity(nightInvestigateActivity);
        }

    }
}
