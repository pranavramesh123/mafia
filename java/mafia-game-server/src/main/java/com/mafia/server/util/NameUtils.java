/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import java.util.regex.Pattern;

/**
 *
 * @author Just1689
 */
public class NameUtils {

    public static boolean isNameOk(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        Pattern p = Pattern.compile("[^a-zA-Z]");
        boolean invalid = p.matcher(name).find();
        if (invalid) {
            return false;
        }

        String sub = name.substring(1);
        String subL = sub.toLowerCase();
        if (!sub.equals(subL)) {
            return false;
        }

        if (name.length() > 10) {
            return false;
        }

        return true;
    }
}
