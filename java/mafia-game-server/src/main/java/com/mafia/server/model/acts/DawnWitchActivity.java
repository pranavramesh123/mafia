/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.ActivityCycler;
import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.ChatMessage;
import com.mafia.server.model.comm.server.Messagebox;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.INDIVIDUAL;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class DawnWitchActivity extends Activity {

    public DawnWitchActivity() {
        super(100, INDIVIDUAL, null);
    }

    @Override
    public void vote(Player player, String vote) {
        if (vote == null) {
            getVotes().remove(player);
            MessageRouter.sendMessage(player, new ChatMessage("You removed your vote.<br />"));
            return;
        }

        if (!vote.equals("abstain") && (getGame().getPlayerByName(vote) != null)) {
            //Could not find X
            MessageRouter.sendMessage(player, Messagebox.createMessageBoxError("Could not find player", vote));
            return;
        }

        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());

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
