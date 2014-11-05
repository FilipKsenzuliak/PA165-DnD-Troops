/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

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
    
    private Mission mission;
    
    private Long amountOfMoney;
    
    private List<HeroDTO> heroes;
            
    public TroopDTO() {
        
    }
    
    public TroopDTO(Long id) {
        this(id, null, null, null, null);
    }
    
    public TroopDTO(String name, Mission mission, Long amountOfMoney, List<HeroDTO> heroes) {
        this(-1L, name, mission, amountOfMoney, heroes);
    }
    
    public TroopDTO(Long id, String name, Mission mission, Long amountOfMoney, List<HeroDTO> heroes) {
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

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
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
