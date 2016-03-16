/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import com.mafia.server.io.MessageRouter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Just1689
 */
public class ReflectionUtils {

    public static Class getClassByName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReflectionUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static Object newObject(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(MessageRouter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MessageRouter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
