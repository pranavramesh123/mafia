/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.bus.actions.KickPlayerAction;
import com.mafia.server.bus.actions.VoteAction;
import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.client.KickPlayer;
import com.mafia.server.model.comm.client.Vote;
import com.mafia.server.model.comm.server.ChatMessage;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.DAY;
import static com.mafia.server.model.state.MafiaTypes.ACTIVITY_PHASE.NIGHT;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.CIVILIAN;
import static com.mafia.server.model.state.MafiaTypes.PLAYER_ROLES.KILLER;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class ChatEvents {

    public static void handleChatMessageFromClients(Player creator, String message) {
        Game game = creator.getGame();

        StringBuilder out = new StringBuilder();
        if (message.startsWith("\\me")) {
            out.append("<i>").append(creator.getName());
            message = message.substring(3);
            out.append(message);
            out.append("</i>");
        } else if (message.startsWith("\\kick")) {
            message = message.substring(6);
            KickPlayer kickPlayer = new KickPlayer();
            kickPlayer.setPlayer(message);
            KickPlayerAction kickPlayerAction = new KickPlayerAction();
            kickPlayerAction.setData(kickPlayer, creator.getSessionId());
            kickPlayerAction.run();
        } else if (message.startsWith("\\vote")) {
            message = message.substring(6);
            System.out.println("Vote for: " + message);
            Vote vote = new Vote();
            vote.setPlayer(message);
            VoteAction voteAction = new VoteAction();
            voteAction.setData(vote, creator.getSessionId());
            voteAction.run();
            return;
        } else {
            if (creator.getRole().equals(CIVILIAN) && !creator.getGame().getActivityPhase().equals(DAY)) {
                MessageRouter.sendMessage(creator, Messagebox.createMessageBoxError("You are sleeping!", ""));
                return;
            }
            out.append("> ");
            out.append("<font color='blue'>");
            out.append(creator.getName());
            out.append("</font>");
            out.append(": ");
            out.append(message);
        }
        out.append("<br />");

        if (creator.getRole().equals(KILLER) && creator.getGame().getActivityPhase().equals(NIGHT)) {
            ChatMessage chatMessage = new ChatMessage(out.toString());
            MessageRouter.sendMessage(game.getPlayersWithRole(KILLER), chatMessage);
            return;
        }

        ChatMessage chatMessage = new ChatMessage(out.toString());
        MessageRouter.sendMessage(game, chatMessage);

    }
}
