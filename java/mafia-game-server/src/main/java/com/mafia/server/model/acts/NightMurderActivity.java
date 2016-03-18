/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.GROUP;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;

/**
 *
 * @author Just1689
 */
public class NightMurderActivity extends Activity {
    
    public NightMurderActivity(ArrayList<Player> players, boolean assignToPlayer) {
        super(100, GROUP, players);
        if (assignToPlayer) {
            for (Player player : players) {
                player.setActivity(this);
            }
        }
    }
    
    @Override
    public void vote(Player player, String vote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
