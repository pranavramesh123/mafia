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
        System.out.println("Got here");
        if (vote == null) {
            getVotes().remove(player);
            MessageRouter.sendMessage(player, new ChatMessage("You removed your vote.<br />"));
            return;
        }

        System.out.println("Got here 2");
        if (vote.equals("abstain")) {
            getVotes().put(player, vote);
            ActivityCycler.checkGame(player.getGame());
            return;
        }

        System.out.println("Got here 3");
        Game game = getGame();
        System.out.println("Got here 3.10");
                
        if (game.getPlayerByName(vote) == null) {
            System.out.println("Got here 3.11");
            //Could not find X
            MessageRouter.sendMessage(player, Messagebox.createMessageBoxError("Could not find player", vote));
            return;
        }
        
        System.out.println("Got here 4");
        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());

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
                getGame().addToChoppingBlock(player);

            }
        }
    }

    @Override
    public boolean isDone() {
        boolean result = getVotes().size() == 1;
        System.out.println("Activity WITCH " + result);
        return result;

    }

}
