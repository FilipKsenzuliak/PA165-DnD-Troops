/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Mission;
import java.util.List;

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
    
    
}
