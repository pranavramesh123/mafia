/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.state;

import com.mafia.server.ConcurrentArrayList;

/**
 *
 * @author w1428368
 */
public class Repository {

    private ConcurrentArrayList<Game> games;

    public static final Repository instance = new Repository();

    public Repository() {
        games = new ConcurrentArrayList<>();
    }

}
