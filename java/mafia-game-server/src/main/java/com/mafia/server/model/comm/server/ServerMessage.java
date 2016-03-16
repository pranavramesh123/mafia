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
public interface ServerMessage {

    /**
     * @return the type
     */
    public String getType();

    /**
     * @param type the type to set
     */
    public void setType(String type);

    /**
     * @return the event
     */
    public String getEvent();

    /**
     * @param event the event to set
     */
    public void setEvent(String event);
}
