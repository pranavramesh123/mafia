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
public class ChatMessage extends ServerMessage {

    private String line;

    public ChatMessage(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Message: " + line;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }

}
