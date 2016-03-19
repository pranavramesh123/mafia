/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.bus.notify.NotifyViewState;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.acts.DawnWitchActivity;
import com.mafia.server.model.acts.DayLynchActivity;
import com.mafia.server.model.acts.NightInvestigateActivity;
import com.mafia.server.model.acts.NightKillerActivity;
import com.mafia.server.model.acts.NightWitchActivity;
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
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.WITCH_TYPE;
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
            moveGameToGamePhase(game, ACTIVITY);

            return;
        }

        if (game.getGamePhase().equals(ACTIVITY)) {
            if (game.getActivityPhase().equals(NIGHT)) {
                moveGameToActivity(game, DAWN);
                return;
            } else if (game.getActivityPhase().equals(DAWN)) {
                moveGameToActivity(game, DAY);
                return;
            } else if (game.getActivityPhase().equals(DAY)) {
                moveGameToActivity(game, NIGHT);
                return;
            }

            return;
        }

        if (game.getActivityPhase().equals(DAWN)) {
            moveGameToActivity(game, DAY);
            return;
        }

    }

    private static void moveGameToGamePhase(Game game, MafiaTypes.GAME_PHASE phase) {
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
        game.executeActivities();
        game.removeActivities();

        switch (activityPhase) {
            case NONE:

                break;
            case NIGHT:
                moveGameToActivityNight(game);
                break;
            case DAWN:
                moveGameToActivityDawn(game);
                break;
            case DAY:
                moveGameToActivityDay(game);
                break;
            default:

                break;

        }

    }

    private static void moveGameToActivityNight(Game game) {
        game.setActivityPhase(NIGHT);

        Checker checker = new Checker<Player>() {
            public boolean check(Player player) {
                return !player.isAlive();
            }
        };
        ArrayListUtils<Player> utils = new ArrayListUtils<>();

        //Handle Killers
        ArrayList<Player> killerPlayers = game.getPlayersWithRole(KILLER);
        utils.removeSome(killerPlayers, checker);
        NightKillerActivity nightkillerActivity = new NightKillerActivity();
        for (Player player : killerPlayers) {
            nightkillerActivity.getPlayers().put(player.getSessionId(), player);
            player.setActivity(nightkillerActivity);
            System.out.println("Added NightMurderA to " + player.getName());
        }
        game.addActivity(nightkillerActivity);

        //Handle investigators
        ArrayList<Player> investigatorPlayers = game.getPlayersWithRole(INVESTIGATOR);
        utils.removeSome(investigatorPlayers, checker);
        for (Player player : investigatorPlayers) {
            NightInvestigateActivity nightInvestigateActivity = new NightInvestigateActivity(player, true);
            game.addActivity(nightInvestigateActivity);
            System.out.println("Added NightInvestA to " + player.getName());
        }

        //Handle WITCH  killers
        ArrayList<Player> witchPlayers = game.getPlayersWithRole(WITCH_TYPE);
        utils.removeSome(witchPlayers, checker);
        for (Player player : witchPlayers) {
            NightWitchActivity nightWitchActivity = new NightWitchActivity();
            game.addActivity(nightWitchActivity);
            player.setActivity(nightWitchActivity);
            nightWitchActivity.getPlayers().put(player.getSessionId(), player);
            System.out.println("Added NightWitchrA to " + player.getName());
        }

        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now night time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);

    }

    private static void moveGameToActivityDawn(Game game) {
        game.setActivityPhase(DAWN);

        Checker checker = new Checker<Player>() {
            public boolean check(Player player) {
                return !player.isAlive();
            }
        };
        ArrayListUtils<Player> utils = new ArrayListUtils<>();

        //Handle witch saving people
        ArrayList<Player> playersAboutToDie = game.getPlayersAboutToDie();
        System.out.println("Players about to die: " + playersAboutToDie.size());
        ArrayList<Player> witches = game.getPlayersWithRole(WITCH_TYPE);
        utils.removeSome(witches, checker);
        System.out.println("Witches: " + witches.size());

        if (witches.size() > 0 && playersAboutToDie.size() > 0) {
            for (Player witch : witches) {
                DawnWitchActivity dawnWitchActivity = new DawnWitchActivity();
                dawnWitchActivity.getPlayers().put(witch.getSessionId(), witch);
                witch.setActivity(dawnWitchActivity);
                for (Player victim : playersAboutToDie) {
                    MessageRouter.sendMessage(witch, new ChatMessage(victim.getName() + " may be resurrected<br />"));
                }
                game.addActivity(dawnWitchActivity);
                System.out.println("Added witch save activity to game ");
            }
        } else {
            System.out.println("No witches or no dying");
        }

        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now dawn time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);

        //Check incase no dawn required
        ActivityCycler.checkGame(game);

    }

    private static void moveGameToActivityDay(Game game) {
        game.setActivityPhase(DAY);

        ArrayList<Player> playersAboutToDie = game.getPlayersAboutToDie();
        for (Player player : playersAboutToDie) {
            player.setAlive(false);
            MessageRouter.sendMessage(game, new ChatMessage(player.getName() + " has died<br />"));
        }

        DayLynchActivity dayLynchActivity = new DayLynchActivity();
        game.addActivity(dayLynchActivity);
        for (Player player : game.getPlayersAsList()) {
            if (player.isAlive()) {
                dayLynchActivity.getPlayers().put(player.getSessionId(), player);
                player.setActivity(dayLynchActivity);
            }
        }

        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now day time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);

        //Check incase no dawn required
        ActivityCycler.checkGame(game);

    }
}
