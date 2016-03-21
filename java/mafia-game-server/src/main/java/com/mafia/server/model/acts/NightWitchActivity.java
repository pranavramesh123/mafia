/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.CycleActivity;
import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.ChatMessage;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.INDIVIDUAL;
import com.mafia.server.model.state.Player;
import java.util.Enumeration;

/**
 *
 * @author Just1689 This activity is for killing someone
 */
public class NightWitchActivity extends Activity {

    public NightWitchActivity() {
        super(100, INDIVIDUAL, null);
    }

    @Override
    public void vote(Player player, String vote) {
        if (vote == null) {
            getVotes().remove(player);
            MessageRouter.sendMessage(player, new ChatMessage("You removed your vote.<br />"));
            return;
        }

        if (vote.equals("abstain")) {
            getVotes().put(player, vote);
            CycleActivity.checkGame(player.getGame());
            return;
        }

        Game game = getGame();
        if (game.getPlayerByName(vote) == null) {
            //Could not find X
            MessageRouter.sendMessage(player, Messagebox.createMessageBoxError("Could not find player", vote));
            return;
        }

        MessageRouter.sendMessage(player, new ChatMessage("You voted for " + vote + ".<br />"));
        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        CycleActivity.checkGame(player.getGame());

    }

    @Override
    public void execute() {
        Enumeration<String> elements = getVotes().elements();
        if (elements.hasMoreElements()) {
            String vote = elements.nextElement();
            if (vote.equals("abstrain")) {
                MessageRouter.sendMessage(getAPlayer(), new ChatMessage("You have abstained"));
            } else {
                Player player = getGame().getPlayerByName(vote);
                if (player == null) {
                    System.err.println("Could not find player " + vote + "(NightWitchActivity.execute)");
                    return;
                }
                System.out.println("Added " + player.getName() + " to chopping block (Witch)");
                getGame().addToChoppingBlock(player);

            }
        }
    }

    @Override
    public boolean isDone() {
        boolean result = getVotes().size() == 1;
        return result;

    }

}
