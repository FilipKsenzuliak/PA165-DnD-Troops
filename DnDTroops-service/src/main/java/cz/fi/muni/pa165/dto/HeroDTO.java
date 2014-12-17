/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Race;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip Ksenzuliak
 */
public class HeroDTO implements Serializable {

    private long id;
    private String name;
    private List<RoleDTO> role = new LinkedList<RoleDTO>();
    private int rank;

    private TroopDTO troop = null;

    private String race;

    public HeroDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }

    public List<RoleDTO> getRole() {
        return role;
    }

    public void setRole(List<RoleDTO> val) {
        this.role = val;
    }

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO val) {
        this.troop = val;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int val) {
        this.rank = val;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HeroDTO other = (HeroDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}