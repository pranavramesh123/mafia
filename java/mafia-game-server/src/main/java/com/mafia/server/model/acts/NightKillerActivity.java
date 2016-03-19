/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.ActivityCycler;
import com.mafia.server.bus.events.PlayerEvents;
import com.mafia.server.bus.notify.NotifyGame;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.ChatMessage;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.GROUP;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.KILLER;
import com.mafia.server.model.state.Player;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public class NightKillerActivity extends Activity {

    public NightKillerActivity() {
        super(100, GROUP, null);
    }

    @Override
    public void vote(Player player, String vote) {
        if (vote == null) {
            getVotes().remove(player);
            MessageRouter.sendMessage(player.getGame().getPlayersWithRole(KILLER), new ChatMessage(player.getName() + " removed their vote."));
            return;
        }

        getVotes().put(player, vote);
        NotifyGame.sendPlayerList(player.getGame());
        ActivityCycler.checkGame(player.getGame());

    }

    @Override
    public boolean isDone() {
        if (getPlayers().size() == getVotes().size()) {
            if (getConcensusPercentage() == 100) {
                Enumeration<String> elements = getVotes().elements();
                String last = null;
                if (elements.hasMoreElements()) {
                    last = elements.nextElement();
                }
                while (elements.hasMoreElements()) {
                    String next = elements.nextElement();
                    if (!next.equals(last)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        ConcurrentHashMap<Player, String> votes = getVotes();
        Set<Player> players = votes.keySet();
        Iterator<Player> iterator = players.iterator();
        iterator.hasNext();
        Player player = iterator.next();

        String playerName = votes.get(player);
        Player playerToKill = player.getGame().getPlayerByName(playerName);

        PlayerEvents.playerDies(playerToKill);

    }

}
