/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

/**
 *
 * @author Just1689
 */
public class MafiaTypes {

    public enum PLAYER_ROLES {

        NONE, CIVILIAN, KILLER, DETECTIVE, VIGILANTE
    }

    public enum GAME_PHASE {

        PREGAME, ACTION, POSTGAME
    }

    public enum ACTION_PHASE {

        NONE, NIGHT, DAY
    }

}
