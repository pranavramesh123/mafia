/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.ActivityCycler;
import com.mafia.server.model.state.MafiaTypes;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Just1689
 */
public class StartGameActivity extends Activity {

    public StartGameActivity(Collection<Player> players) {
        super(100, MafiaTypes.ACTIVITY_PARTICIPATION.GROUP, new ArrayList<>(players));
    }

    @Override
    public void vote(Player player, String vote) {
        getVotes().put(player, vote);
        ActivityCycler.checkGame(player.getGame());

    }

    @Override
    public boolean isDone() {
        return getPlayers().size() == getVotes().size();
    }

    public void addPlayer(Player player) {
        getPlayers().put(player.getSessionId(), player);
    }

}
