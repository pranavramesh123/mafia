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

        //Handle Killers
        ArrayList<Player> killerPlayers = game.getPlayersWithRole(KILLER);
        new ArrayListUtils<Player>().removeSome(killerPlayers, checker);
        NightKillerActivity nightkillerActivity = new NightKillerActivity();
        for (Player player : killerPlayers) {
            nightkillerActivity.getPlayers().put(player.getSessionId(), player);
            player.setActivity(nightkillerActivity);
            System.out.println("Added NightMurderA to " + player.getName() );
        }
        game.addActivity(nightkillerActivity);

        //Handle investigators
        ArrayList<Player> investigatorPlayers = game.getPlayersWithRole(INVESTIGATOR);
        for (Player player : investigatorPlayers) {
            NightInvestigateActivity nightInvestigateActivity = new NightInvestigateActivity(player, true);
            game.addActivity(nightInvestigateActivity);
            System.out.println("Added NightInvestA to " + player.getName() );
        }

        //Handle WITCH  killers
        ArrayList<Player> witchPlayers = game.getPlayersWithRole(WITCH_TYPE);
        for (Player player : witchPlayers) {
            NightWitchActivity nightWitchActivity = new NightWitchActivity();
            game.addActivity(nightWitchActivity);
            player.setActivity(nightWitchActivity);
            nightWitchActivity.getPlayers().put(player.getSessionId(), player);
            System.out.println("Added NightWitchrA to " + player.getName() );
        }
        
        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now night time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);
        
    }
    
    private static void moveGameToActivityDawn(Game game) {
        game.setActivityPhase(DAWN);

        //Handle investigators
        ArrayList<Player> witchPlayers = game.getPlayersWithRole(WITCH_TYPE);
        for (Player player : witchPlayers) {
            DawnWitchActivity dawnWitchActivity = new DawnWitchActivity();
            dawnWitchActivity.getPlayers().put(player.getSessionId(), player);
            player.setActivity(dawnWitchActivity);
            game.addActivity(dawnWitchActivity);
        }
        
        MessageRouter.sendMessage(game, new ChatMessage("<strong>***It is now dawn time***</strong><br />"));
        NotifyViewState.nofity(game);
        NotifyGame.sendPlayerList(game);
    }
}
