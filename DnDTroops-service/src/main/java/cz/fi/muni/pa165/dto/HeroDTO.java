/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Race;
import cz.fi.muni.pa165.entity.Troop;
import java.util.List;

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
    
    private Troop troop;
    
    private String name;
    
    public HeroDTO(){
    }
    
    public HeroDTO(Long id) {
        this(id, null, null, null, null, null, null);
    }
    
    public HeroDTO(Race race, Long age,
                   Long rank, String name,
                   Troop troop, List<RoleDTO> role) {
        this(-1L, race, age, rank, name, troop, role);
    }
    
    public HeroDTO(Long id, Race race,
                   Long age, Long rank, String name,
                   Troop troop, List<RoleDTO> role) {
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

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
