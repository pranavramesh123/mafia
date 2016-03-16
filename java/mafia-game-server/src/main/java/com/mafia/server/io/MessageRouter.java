/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.bus.events.Event;
import com.mafia.server.model.comm.client.MafiaMessage;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;
import com.mafia.server.util.JacksonUtil;
import com.mafia.server.util.ReflectionUtils;

/**
 *
 * @author Just1689
 */
public class MessageRouter {

    public static void handle(String message, String sessionId) {

        //Get an object with the type and the 
        JacksonUtil<MafiaMessage> util = new JacksonUtil<>();
        MafiaMessage mafiaMessage = util.stringToObject(message, MafiaMessage.class);

        //Get the data
        Class clazz = ReflectionUtils.getClassByName("com.mafia.server.model.comm.client." + mafiaMessage.getType());
        Object data = new JacksonUtil<>().stringToObject(message, clazz);

        //Get the event
        Class eventClass = ReflectionUtils.getClassByName("com.mafia.server.bus.events." + mafiaMessage.getEvent());
        Object event = ReflectionUtils.newObject(eventClass);

        //Put the data into the event and run it
        ((Event) event).setData(data, sessionId);
        ((Runnable) event).run();

    }

    public static void sendMessage(Game game, Object t) {
        String json = new JacksonUtil().objectToString(t);
        MessageHandler.sendMessage(game, json);
    }

    public static void sendMessage(Player player, Object t) {
        String json = new JacksonUtil().objectToString(t);
        MessageHandler.sendMessage(player, json);
    }

}
