/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.bus;

import com.mafia.server.state.Game;
import com.mafia.server.state.Repository;
import javax.websocket.Session;

/**
 *
 * @author w1428368
 */
public class PlayerEvents {
    
    public static synchronized void playerQuits(Session session) {
        Game game = Repository.getGameBySession(session);
        game.removePlayer(session);
        
        //Todo: notify?
        
    }
    
}
