/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.bus.notify.NotifyViewState;
import com.mafia.server.io.MessageHandler;
import com.mafia.server.model.state.Game;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.CIVILIAN;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.KILLER;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;
import com.mafia.server.util.ArrayListUtils;
import com.mafia.server.util.StringUtils;
import java.util.ArrayList;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class GameEvents {

    public static void create(String playerName, String playerPassCode, String createdBy) {
        Player player = PlayerEvents.makePlayer(playerName, playerPassCode, createdBy);
        Repository.addPlayer(player);
        createGame(player);
    }

    private static synchronized void createGame(Player player) {

        //Create a unique key
        String newKey = StringUtils.makeUniqueKey(5);
        while (Repository.getGameByKey(newKey) != null) {
            newKey = StringUtils.makeUniqueKey(5);
        }

        Game game = new Game(player, newKey);
        Repository.createGame(game);

        PlayerEvents.joinGame(player, game);

        NotifyViewState.nofity(game);
        NotifyGame.notifyCreatorOfGameCode(player.getSessionId(), game.getKey());

    }

    public static void newPlayerJoinsGame(String name, String passCode, String createdBy, Game game) {
        Player player = PlayerEvents.makePlayer(name, passCode, createdBy);
        Repository.addPlayer(player);
        MessageboxEvents.showMessageboxTimed(game, player.getName(), "has joined");
        PlayerEvents.joinGame(player, game);
        NotifyGame.sendPlayerList(game);

    }

    public static void kickPlayer(Player player, String playerName) {

        //Get the game
        Game game = player.getGame();

        //Check if game ok?
        if (game == null) {
            System.out.println("Error: No game (GameEvents.kickPlayer()");
            return;
        }

        if (player.equals(game.getCreator())) {

            Player playerToKick = game.getPlayerByName(playerName);
            if (playerToKick != null) {
                Session session = Repository.getSessionByPlayer(playerToKick);
                MessageHandler.closeSession(session);
            }
        } else {
            MessageboxEvents.notifyOfFail("Error", "You are not creator", player);
        }

        NotifyGame.sendPlayerList(game);

    }

    public static void checkGame(Game game) {
        if (game.isAbandoned()) {
            Repository.removeGame(game);
        }
    }

    public static void assignRoles(Game game) {
        //To implement
        ArrayList<Player> players = (ArrayList<Player>) game.getPlayersAsList();
        new ArrayListUtils<Player>().orderRandomly(players);

        boolean civ = false;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (i == 0) {
                player.setRole(CIVILIAN);
                continue;
            }

            civ = !civ;

            if (civ) {
                player.setRole(CIVILIAN);
                continue;
            }

            player.setRole(KILLER);

        }
        NotifyGame.nofityOfRole(game);
    }

}
