/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.MafiaTypes;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Just1689
 */
public class DayLynchActivity extends Activity {

    public DayLynchActivity(Collection<Player> players) {
        super(100, MafiaTypes.ACTIVITY_PARTICIPATION.GROUP, new ArrayList<>(players));
    }

    @Override
    public void vote(Player player, String vote) {
        Game game = player.getGame();
        if (game == null) {
            System.out.println("No game found (DayLynchActivity.vote)");
            return;
        }

    }

    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
