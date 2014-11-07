/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Mission;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
public class TroopDTO {
    
    private Long id;
    
    private String name;
    
    private MissionDTO mission;
    
    private Long amountOfMoney;
    
    private List<HeroDTO> heroes;
            
    public TroopDTO() {
        
    }
    
    public TroopDTO(String name, MissionDTO mission, Long amountOfMoney, List<HeroDTO> heroes) {
        this(-1L, name, mission, amountOfMoney, heroes);
    }
    
    public TroopDTO(Long id, String name, MissionDTO mission, Long amountOfMoney, List<HeroDTO> heroes) {
        this.id = id;
        this.amountOfMoney = amountOfMoney;
        this.heroes = heroes;
        this.mission = mission;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MissionDTO getMission() {
        return mission;
    }

    public void setMission(MissionDTO mission) {
        this.mission = mission;
    }

    public Long getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public List<HeroDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.mission);
        hash = 67 * hash + Objects.hashCode(this.amountOfMoney);
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
        final TroopDTO other = (TroopDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mission, other.mission)) {
            return false;
        }
        if (!Objects.equals(this.amountOfMoney, other.amountOfMoney)) {
            return false;
        }
        return true;
    }
    
    
}
