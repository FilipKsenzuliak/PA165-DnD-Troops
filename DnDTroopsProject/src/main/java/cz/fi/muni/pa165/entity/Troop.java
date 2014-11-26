/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
@Entity(name = "Troop")
public class Troop implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ManyToOne
    private Mission mission;
    
    private Long amountOfMoney;
    
    @OneToMany(mappedBy = "troop")
    private List<Hero> heroes;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
   
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.mission);
        hash = 79 * hash + Objects.hashCode(this.amountOfMoney);
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
        final Troop other = (Troop) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mission, other.mission)) {
            return false;
        }
        return Objects.equals(this.getAmountOfMoney(), other.getAmountOfMoney());
    }

    @Override
    public String toString() {
        return "Troop{" + "id=" + id + ", name=" + name + ", mission=" + mission + ", amountOfMoney=" + amountOfMoney + '}';
    }
}
