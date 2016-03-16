/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.model.comm.MafiaMessage;
import com.mafia.server.model.state.Player;
import com.mafia.server.util.JacksonUtil;
import com.mafia.server.util.ReflectionUtils;

/**
 *
 * @author Just1689
 */
public class MessageRouter {

    public static void handle(String message, Player source) {
        JacksonUtil<MafiaMessage> util = new JacksonUtil<>();
        MafiaMessage mafiaMessage = util.stringToObject(message, MafiaMessage.class);
        String action = mafiaMessage.getAction();

        Class clazz = ReflectionUtils.getClassByName("com.mafia.server.model.comm.client." + action);
        Object result = new JacksonUtil<>().stringToObject(message, clazz);

    }
}
