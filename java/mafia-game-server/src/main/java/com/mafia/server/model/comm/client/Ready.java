/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.comm.client;

import com.mafia.server.util.JacksonUtils;

/**
 *
 * @author Just1689
 */
public class Ready {

    private boolean ok;

    /**
     * @return the ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return JacksonUtils.objectToString(this);
    }

}
