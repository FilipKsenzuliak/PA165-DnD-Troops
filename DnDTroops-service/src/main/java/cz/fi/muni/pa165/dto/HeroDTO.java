/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Race;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip Ksenzuliak
 */
public class HeroDTO {
    
    private Long id;
    private Race race;
    private Long age;
    private Long rank;
    private List<RoleDTO> role;
    private TroopDTO troop;
    private String name;
    
    public HeroDTO(){
    }
    
    public HeroDTO(Race race, Long age, Long rank,
                   String name, TroopDTO troop, List<RoleDTO> role) {
        
        this(-1L, race, age, rank, name, troop, role);
    }
    
    public HeroDTO(Long id, Race race, Long age, Long rank,
                   String name, TroopDTO troop, List<RoleDTO> role) {
        
        this.id = id;
        this.race = race;
        this.age = age;
        this.rank = rank;
        this.name = name;
        this.troop = troop;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public List<RoleDTO> getRole() {
        return role;
    }

    public void setRole(List<RoleDTO> role) {
        this.role = role;
    }

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO troop) {
        this.troop = troop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.race != null ? this.race.hashCode() : 0);
        hash = 67 * hash + Objects.hashCode(this.age);
        hash = 67 * hash + Objects.hashCode(this.rank);
        hash = 67 * hash + Objects.hashCode(this.name);
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
        if (this.race != other.race) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        if (!Objects.equals(this.troop, other.troop)) {
            return false;
        }
        return true;
    }
    
}
