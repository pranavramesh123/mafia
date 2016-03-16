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
public class CreateGame {

    private String name;
    private String passCode;

    public String getName() {
        return name;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

}
