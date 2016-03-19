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

/**
 *
 * @author Just1689
 */
public class NightInvestigateActivity extends Activity {

    public NightInvestigateActivity(Player player, boolean assignToPlayer) {
        super(100, INDIVIDUAL);
        getPlayers().put(player.getSessionId(), player);
        if (assignToPlayer) {
            player.setActivity(this);
        }
    }

    @Override
    public void vote(Player player, String vote) {
        Game game = player.getGame();
        if (game == null) {
            //Game not found
            return;
        }
        Player playerSelected = game.getPlayerByName(vote);
        if (playerSelected == null) {
            MessageRouter.sendMessage(player, Messagebox.createMessageBoxError("Error", "Player does not exist: " + vote));
            return;
        }

        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());

    }

    @Override
    public boolean isDone() {
        boolean result = getVotes().size() == 1;
        return result;

    }

    @Override
    public void execute() {
        Player votePlayer = getVotedPlayer();
        if (votePlayer != null) {
            MessageRouter.sendMessage(
                    getAPlayer(),
                    new ChatMessage("<font color='red'>" + votePlayer.getName() + " is a " + votePlayer.getRole().getTeam().name() + "</font><br />")
            );
        } else {
            System.err.println("Could not find voted player (NightInvestigator)");
        }
    }

}
