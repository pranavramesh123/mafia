/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.bus.actions.Action;
import com.mafia.server.model.comm.client.MafiaMessage;
import com.mafia.server.model.comm.server.ServerMessage;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.util.JacksonUtils;
import com.mafia.server.util.ReflectionUtils;
import java.util.List;

/**
 *
 * @author Just1689
 */
public class MessageRouter {

    public static void handleIncoming(String message, String sessionId) {

        //Get an object with the type and the 
        JacksonUtils<MafiaMessage> util = new JacksonUtils<>();
        MafiaMessage mafiaMessage = util.stringToObject(message, MafiaMessage.class);
        System.out.println("Received: " + message);

        //Get the data
        Class clazz = ReflectionUtils.getClassByName("com.mafia.server.model.comm.client." + mafiaMessage.getType());
        Object data = new JacksonUtils<>().stringToObject(message, clazz);

        //Get the action
        Class actionClass = ReflectionUtils.getClassByName("com.mafia.server.bus.actions." + mafiaMessage.getAction());
        Object action = ReflectionUtils.newObject(actionClass);

        //Put the data into the action and run it
        ((Action) action).setData(data, sessionId);
        ((Runnable) action).run();

    }

    public static void sendMessage(Game game, ServerMessage serverMessage) {
        serverMessage.resolveEventName();
        String json = JacksonUtils.objectToString(serverMessage);
        MessageHandler.sendMessage(game, json);

    }

    public static void sendMessage(Player player, ServerMessage serverMessage) {
        serverMessage.resolveEventName();
        String json = JacksonUtils.objectToString(serverMessage);
        MessageHandler.sendMessage(player, json);

    }

    public static void sendMessage(List<Player> players, ServerMessage serverMessage) {
        if ((players != null) && (!players.isEmpty())) {
            for (Player player : players) {
                sendMessage(player, serverMessage);
            }
        }
    }

}
