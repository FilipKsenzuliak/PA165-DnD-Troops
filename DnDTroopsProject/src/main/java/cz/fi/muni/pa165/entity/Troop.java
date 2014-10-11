/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
@Entity
public class Troop {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    private String mission;
    
    private int amountOfMoney;
   
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mission
     */
    public String getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(String mission) {
        this.mission = mission;
    }

    /**
     * @return the amountOfMoney
     */
    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    /**
     * @param amountOfMoney the amountOfMoney to set
     */
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
        return "Troop{" + "name=" + name + ", mission=" + mission + ", amountOfMoney=" + amountOfMoney + '}';
    }
}
