/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.client;

/**
 *
 * @author Just1689
 */
public class JoinGame {

    private String name;
    private String passCode;
    private String key;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

}
