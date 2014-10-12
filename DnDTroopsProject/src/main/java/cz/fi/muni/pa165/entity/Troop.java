/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.entity.Mission;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
@Entity
public class Troop {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    private Mission mission;
    
    @Column(nullable = false)
    private int amountOfMoney;
    
    @OneToMany(mappedBy = "troop")
    private List<Hero> heroes;
   
    public Long getId() {
        return id;
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

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.getName());
        hash = 17 * hash + Objects.hashCode(this.getMission());
        hash = 17 * hash + this.getAmountOfMoney();
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
        if (this.getAmountOfMoney() != other.getAmountOfMoney()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Troop{" + "id=" + id + ", name=" + name + ", mission=" + mission + ", amountOfMoney=" + amountOfMoney + '}';
    }
}
