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
import com.mafia.server.model.state.MafiaTypes;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.KILLER;
import com.mafia.server.model.state.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Just1689
 */
public class DayLynchActivity extends Activity {

    public DayLynchActivity() {
        super(50, MafiaTypes.ACTIVITY_PARTICIPATION.GROUP, null);
    }

    @Override
    public void vote(Player player, String vote) {
        Game game = player.getGame();
        if (game == null) {
            System.out.println("No game found (DayLynchActivity.vote)");
            return;
        }

        if (vote == null) {
            getVotes().remove(player);
            MessageRouter.sendMessage(game, new ChatMessage(player.getName() + " removed their vote.<br />"));
            return;
        }

        if (!vote.equals("abstain") && (getGame().getPlayerByName(vote) == null)) {
            //Could not find X
            MessageRouter.sendMessage(player, Messagebox.createMessageBoxError("Could not find player", vote));
            return;
        }

        getVotes().put(player, vote);
        MessageRouter.sendMessage(player.getGame().getPlayersAsList(), new ChatMessage(player.getName() + " voted for " + vote + "<br />"));
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());

    }

    @Override
    public boolean isDone() {
        boolean enoughVotes = getVotes().size() >= getGame().getPlayersWhoAreAlive().size() / 2;
        if (!enoughVotes) {
            return false;
        }

        int needed = getGame().getPlayersWhoAreAlive().size() / 2;
        System.out.println("Need " + needed + " votes to kill");
        HashMap<String, Integer> totals = new HashMap<>();
        for (Map.Entry<Player, String> entry : getVotes().entrySet()) {
            Player player = entry.getKey();
            String vote = entry.getValue();
            Integer i = totals.get(vote);
            if (i == null) {
                totals.put(vote, 0);
                i = totals.get(vote);
            }
            i++;
            totals.put(vote, i);
        }

        for (Map.Entry<String, Integer> entry : totals.entrySet()) {
            if (entry.getValue() >= needed) {
                //Found!
                return true;
            }
        }
        return false;

    }

    @Override
    public void execute() {
        int needed = getGame().getPlayersWhoAreAlive().size() / 2;
        HashMap<String, Integer> totals = new HashMap<>();
        for (Map.Entry<Player, String> entry : getVotes().entrySet()) {
            Player player = entry.getKey();
            String vote = entry.getValue();
            Integer i = totals.get(vote);
            if (i == null) {
                totals.put(vote, 0);
                i = totals.get(vote);
            }
            i++;
            totals.put(vote, i);
        }

        for (Map.Entry<String, Integer> entry : totals.entrySet()) {
            if (entry.getValue() >= needed) {
                //Found!
                Player player = getGame().getPlayerByName(entry.getKey());
                player.setAlive(false);
                MessageRouter.sendMessage(getGame(), new ChatMessage(player.getName() + " has died<br />"));

            }
        }
    }

}
