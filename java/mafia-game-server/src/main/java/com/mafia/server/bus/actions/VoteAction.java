/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.actions;

import com.mafia.server.model.acts.Activity;
import com.mafia.server.model.comm.client.CreateGame;
import com.mafia.server.model.comm.client.Vote;
import com.mafia.server.model.state.Player;
import com.mafia.server.model.state.Repository;

/**
 *
 * @author Just1689
 */
public class VoteAction implements Runnable, Action {

    private Vote data;
    private String createdBy;

    @Override
    public void run() {
        Player player = Repository.getPlayerBySessionId(createdBy);
        if (player == null) {
            System.out.println("Could not find player (VoteAction.run())");
            return;
        }
        Activity activity = player.getActivity();
        if (activity == null) {
            System.out.println("No activity to use (VoteAction.run())");
            return;
        }

        if (data.getPlayer().equals("not ready")) {
            //Will remove the item from the vote map
            data.setPlayer(null);
        }

        activity.vote(player, data.getPlayer());
    }

    @Override
    public void setData(Object obj, String sessionId) {
        this.data = (Vote) obj;
        this.createdBy = sessionId;

    }

}
