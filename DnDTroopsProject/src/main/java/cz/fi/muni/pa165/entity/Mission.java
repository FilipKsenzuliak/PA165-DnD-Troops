package cz.fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco: 324662
 */
@Entity
public class Mission implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String objective;
    
    private int reward;

    public void setName(String name) {
        this.name = name;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getObjective() {
        return objective;
    }

    public int getReward() {
        return reward;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.objective);
        hash = 37 * hash + Objects.hashCode(this.reward);
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
        final Mission other = (Mission) obj;
        return this.name.equals(other.name) && (this.objective.equals(other.objective)) && this.reward == other.reward;
    }
    
    @Override
    public String toString(){
        return "Mission[" + name + "]: " + reward + "gold. Objective: " + objective;
    }
    
    
}
