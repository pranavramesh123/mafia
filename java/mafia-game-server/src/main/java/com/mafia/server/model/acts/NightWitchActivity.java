/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.INDIVIDUAL;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class NightWitchActivity extends Activity {

    public NightWitchActivity() {
        super(100, INDIVIDUAL, null);
    }

    @Override
    public void vote(Player player, String vote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
