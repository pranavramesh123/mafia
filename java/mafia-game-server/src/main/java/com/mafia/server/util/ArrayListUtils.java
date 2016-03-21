/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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

    public void order(ArrayList<T> list, Comparator<T> comparator) {
        Collections.sort(list, comparator);
    }


    public void removeSome(ArrayList<T> list, Checker<T> checker) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (checker.check(next)) {
                iterator.remove();
            }
        }
    }

    public static interface Checker<T> {

        public boolean check(T t);
    }
}
