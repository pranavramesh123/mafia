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

    public enum TEAM {

        NONE, CIVILIAN, KILLER;
    }

    public enum PLAYER_ROLES {

        NONE(TEAM.NONE), CIVILIAN(TEAM.CIVILIAN), KILLER(TEAM.KILLER), INVESTIGATOR(TEAM.CIVILIAN), WITCH_TYPE(TEAM.CIVILIAN), VIGILANTE(TEAM.CIVILIAN), HANDICAPPED(TEAM.CIVILIAN), SUICIDE(TEAM.CIVILIAN);

        private final TEAM team;

        PLAYER_ROLES(TEAM teamS) {
            this.team = teamS;
        }

        public TEAM getTeam() {
            return team;
        }
    }

    public enum GAME_PHASE {

        PREGAME, ACTIVITY, POSTGAME
    }

    public enum ACTIVITY_PHASE {

        NONE, NIGHT, DAWN, DAY
    }

    public enum ACTIVITY_PARTICIPATION {

        INDIVIDUAL, GROUP

    }

}
