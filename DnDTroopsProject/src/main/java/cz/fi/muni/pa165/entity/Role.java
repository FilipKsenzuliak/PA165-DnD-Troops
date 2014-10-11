package cz.fi.muni.pa165.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

enum Class {
    WIZARD, BARD, WARRIOR, ROGUE, RANGER, CLERIC, PALADIN
}

/**
 * 
 * @author Dávid Hubac
 * @uco 396042
 */
@Entity
public class Role {
    
    @Id
    @GeneratedValue
    private long id;
        
    @Column(nullable = false)
    private Class name;
    
    private String description;

    public long getId() {
        return id;
    }

    public Class getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(Class name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + Objects.hashCode(this.description);
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
        final Role other = (Role) obj;
        if (this.name != other.name) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }


}
