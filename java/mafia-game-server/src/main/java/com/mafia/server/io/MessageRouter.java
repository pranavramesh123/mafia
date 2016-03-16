/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.io;

import com.mafia.server.bus.events.Event;
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
        
        //Get an object with the type and the 
        JacksonUtil<MafiaMessage> util = new JacksonUtil<>();
        MafiaMessage mafiaMessage = util.stringToObject(message, MafiaMessage.class);
        
        Class clazz = ReflectionUtils.getClassByName("com.mafia.server.model.comm.client." + mafiaMessage.getType());
        Object data = new JacksonUtil<>().stringToObject(message, clazz);
        
        Class eventClass = ReflectionUtils.getClassByName("com.mafia.server.bus.events." + mafiaMessage.getEvent());
        Object event = ReflectionUtils.newObject(eventClass);
        
        
        ((Event) event).setData(data);
        ((Runnable) event).run();
        
    }
    
}
