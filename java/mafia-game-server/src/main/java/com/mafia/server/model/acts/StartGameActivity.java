/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.ActivityCycler;
import com.mafia.server.bus.events.GameEvents;
import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
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
        if (vote == null) {
            getVotes().remove(player);
            NotifyGame.sendPlayerList(player.getGame());
            return;
        }

//        if (!vote.equals("ready") && player.getGame().getPlayerByName(vote) == null) {
//            Messagebox messagebox = Messagebox.createMessageBoxError("Error", "Cannot vote for: " + vote);
//            MessageRouter.sendMessage(player, messagebox);
//            return;
//        }
        if (!vote.equals("ready")) {
            Messagebox messagebox = Messagebox.createMessageBoxError("Error", "Vote ready or not ready");
            MessageRouter.sendMessage(player, messagebox);
            return;
        }

        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());
    }

    @Override
    public boolean isDone() {
        return getPlayers().size() == getVotes().size() && getPlayers().size() >= 4;
    }

    public void addPlayer(Player player) {
        getPlayers().put(player.getSessionId(), player);
    }

    @Override
    public void execute() {
        //
    }

}
