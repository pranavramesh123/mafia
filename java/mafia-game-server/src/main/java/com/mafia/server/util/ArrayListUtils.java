/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Just1689
 */
public class ArrayListUtils<T> {

    public void orderRandomly(ArrayList<T> list) {
        Collections.shuffle(list, new Random());
        Collections.shuffle(list, new Random());
    }
}
