package cz.fi.muni.pa165.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

enum Race {
    ELF, HUMAN, HALFORC, GNOME, DWARF
}

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
    private Race race;
        
    @Column(nullable = false)
    private Class cls;
    
    
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.race);
        hash = 37 * hash + Objects.hashCode(this.cls);
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
        if (this.race != other.race) {
            return false;
        }
        if (this.cls != other.cls) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "race=" + race + ", cls=" + cls + '}';
    }
}
