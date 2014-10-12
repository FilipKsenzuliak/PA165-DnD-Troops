/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@Entity
public class Hero implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Race race;
    private int age;
    private int rank;
    
    @ManyToMany
    private List<Role> role;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Troop troop;
    
    @Column(nullable=false)
    private String name;

    public void setId(Long id) {
        this.id = id;
    }
    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
    
    public Race getRace() {
        return race;
    }

    public Long getId() {
        return id;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.race != null ? this.race.hashCode() : 0);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.role);
        hash = 97 * hash + Objects.hashCode(this.troop);
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
        final Hero other = (Hero) obj;
        if (this.race != other.race) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hero{" + "id=" + id 
                + ", race=" + race + ","
                + " age=" + age + ","
                + " level=" + rank + ","
                + " name=" + name + ","
                + " role=" + role + '}';
    }     
}
