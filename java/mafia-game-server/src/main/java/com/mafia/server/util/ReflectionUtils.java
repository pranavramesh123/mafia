/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

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
}
