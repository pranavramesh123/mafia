/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import com.mafia.server.model.state.Repository;
import java.util.UUID;

/**
 *
 * @author Just1689
 */
public class StringUtils {

    public static String makeUniqueKey(int end) {
        boolean ok = false;
        String key = null;
        while (!ok) {
            key = UUID.randomUUID().toString().toUpperCase().substring(1, end);
            ok = !Repository.games.contains(key);
        }
        return key;
    }

}
