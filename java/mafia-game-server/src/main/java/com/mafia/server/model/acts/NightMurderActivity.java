/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.acts;

import com.mafia.server.bus.events.PlayerEvents;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PARTICIPATION.GROUP;
import com.mafia.server.model.state.Player;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Just1689
 */
public class NightMurderActivity extends Activity {

    public NightMurderActivity(ArrayList<Player> players, boolean assignToPlayer) {
        super(100, GROUP, players);
        if (assignToPlayer) {
            for (Player player : players) {
                player.setActivity(this);
            }
        }
    }

    @Override
    public void vote(Player player, String vote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDone() {
        if (getPlayers().size() == getVotes().size()) {
            if (getConcensusPercentage() == 100) {
                Enumeration<String> elements = getVotes().elements();
                String last=  null;
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
