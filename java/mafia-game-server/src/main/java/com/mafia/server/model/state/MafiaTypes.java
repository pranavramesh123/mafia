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

        NONE(TEAM.NONE, "None"), CIVILIAN(TEAM.CIVILIAN, "Civilian"), KILLER(TEAM.KILLER, "Killer"), INVESTIGATOR(TEAM.CIVILIAN, "Invesigator"), WITCH_TYPE(TEAM.CIVILIAN, "Witch"), VIGILANTE(TEAM.CIVILIAN, "Vigilante"), HANDICAPPED(TEAM.CIVILIAN, "Handicapped"), SUICIDE(TEAM.CIVILIAN, "Suicide");

        private final TEAM team;
        private final String label;

        PLAYER_ROLES(TEAM teamS, String lbl) {
            this.team = teamS;
            this.label = lbl;
        }

        public TEAM getTeam() {
            return team;
        }

        public String getLabel() {
            return label;
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
