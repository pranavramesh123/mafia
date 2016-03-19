/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.bus.notify.NotifyViewState;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.acts.NightInvestigateActivity;
import com.mafia.server.model.acts.NightKillerActivity;
import com.mafia.server.model.comm.server.ChatMessage;
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
import com.mafia.server.util.ArrayListUtils;
import com.mafia.server.util.ArrayListUtils.Checker;
import java.util.ArrayList;

/**
 *
 * @author Just1689
 */
public class ActivityCycler {

    public static void checkGame(Game game) {
        if (game.isActivityComplete()) {
            System.out.println("Activity is complete");
            moveGameToNextSomething(game);
        } else {
            System.out.println("Activity is not complete");
        }
    }

    private static void moveGameToNextSomething(Game game) {
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

        //Handle once off assignment
        if (phase.equals(ACTIVITY)) {
            GameEvents.assignRoles(game);
        }

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

        Checker checker = new Checker<Player>() {
            public boolean check(Player player) {
                return !player.isAlive();
            }
        };
        new ArrayListUtils<Player>().removeSome(killerPlayers, checker);
        NightKillerActivity nightMurderActivity = new NightKillerActivity();
        for (Player player : killerPlayers) {
            nightMurderActivity.getPlayers().put(player.getSessionId(), player);
            player.setActivity(nightMurderActivity);
        }
        game.addActivity(nightMurderActivity);

        //Handle investigators
        ArrayList<Player> investigatorPlayers = game.getPlayersWithRole(INVESTIGATOR);
        for (Player player : investigatorPlayers) {
            NightInvestigateActivity nightInvestigateActivity = new NightInvestigateActivity(player, true);
            game.addActivity(nightInvestigateActivity);
        }

        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now night time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);

    }
}
