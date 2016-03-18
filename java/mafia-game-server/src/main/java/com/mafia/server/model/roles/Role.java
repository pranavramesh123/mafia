/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.roles;

import com.mafia.server.bus.actions.Action;
import java.util.List;

/**
 *
 * @author connavar
 */
public interface Role {

    public String getDescription();

    public List<Action> getActions();

    public void setActions(List<Action> actions);

}
