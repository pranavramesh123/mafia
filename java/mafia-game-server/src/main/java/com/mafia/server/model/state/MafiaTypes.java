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

        NONE, CIVILIAN, KILLER, INVESTIGATOR, WITCH_TYPE, VIGILANTE, HANDICAPPED, SUICIDE
    }

    public enum GAME_PHASE {

        PREGAME, ACTIVITY, POSTGAME
    }

    public enum ACTIVITY_PHASE {

        NONE, NIGHT, DAWN, DAY
    }

}
