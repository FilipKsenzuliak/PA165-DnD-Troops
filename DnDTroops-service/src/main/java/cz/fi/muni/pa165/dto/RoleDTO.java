/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dto;

import java.util.List;

/**
 *
 * @author David Hubac
 */
public class RoleDTO {
    private Long id;
    
    private List<HeroDTO> heroes;
    
    private String name;
    
    private String description;
    
    private int roleLevel = 1;
    
    public RoleDTO() {
        
    }
    
    public RoleDTO(List<HeroDTO> heroes, String name, String description) {
        this(null, heroes, name, description);
    }
    
    public RoleDTO(Long id, List<HeroDTO> heroes, String name, String description) {
        this.id = id;
        this.heroes = heroes;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HeroDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }
    
    
}
