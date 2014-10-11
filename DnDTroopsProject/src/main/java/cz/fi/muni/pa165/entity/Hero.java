/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

enum Race {
    ELF, HUMAN, HALFORC, GNOME, DWARF
}

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@Entity
public class Hero {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private Race race;
    private int age;
    private int level;
    private List<Role> role;
    
    @Column(nullable=false, unique=true)
    private String name;
    
    @ManyToOne
    private Troop troop;
    
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
                + " level=" + level + ","
                + " name=" + name + ","
                + " role=" + role + '}';
    }     
}
