/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.api.dto.MissionDTO;
import cz.fi.muni.pa165.api.dto.RoleDTO;

/**
 *
 * @author Filip
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RoleServiceImpl rsi = new RoleServiceImpl();
        RoleDTO role = new RoleDTO();
        rsi.createRole(role);
    }
}
