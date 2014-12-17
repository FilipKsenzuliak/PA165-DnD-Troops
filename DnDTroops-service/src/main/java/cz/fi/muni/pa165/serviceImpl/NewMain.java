/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dto.MissionDTO;

/**
 *
 * @author Filip
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MissionServiceImpl msi = new MissionServiceImpl();
        MissionDTO mission = new MissionDTO();
        msi.createMission(mission);
    }
}
