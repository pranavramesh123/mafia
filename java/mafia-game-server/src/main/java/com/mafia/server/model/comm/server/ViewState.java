/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.server;

/**
 *
 * @author Just1689
 */
public class ViewState extends ServerMessage {

    private String key;
    private boolean createOrJoin;
    private boolean chat;
    private boolean chatInput;
    private boolean leaveGame;
    private boolean vote;
    private boolean ready;

    /**
     * @return the createOrJoin
     */
    public boolean isCreateOrJoin() {
        return createOrJoin;
    }

    /**
     * @param createOrJoin the createOrJoin to set
     */
    public void setCreateOrJoin(boolean createOrJoin) {
        this.createOrJoin = createOrJoin;
    }

    /**
     * @return the chat
     */
    public boolean isChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(boolean chat) {
        this.chat = chat;
    }

    /**
     * @return the chatInput
     */
    public boolean isChatInput() {
        return chatInput;
    }

    /**
     * @param chatInput the chatInput to set
     */
    public void setChatInput(boolean chatInput) {
        this.chatInput = chatInput;
    }

    /**
     * @return the leaveGame
     */
    public boolean isLeaveGame() {
        return leaveGame;
    }

    /**
     * @param leaveGame the leaveGame to set
     */
    public void setLeaveGame(boolean leaveGame) {
        this.leaveGame = leaveGame;
    }

    /**
     * @return the vote
     */
    public boolean isVote() {
        return vote;
    }

    /**
     * @param vote the vote to set
     */
    public void setVote(boolean vote) {
        this.vote = vote;
    }

    /**
     * @return the ready
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * @param ready the ready to set
     */
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

}
