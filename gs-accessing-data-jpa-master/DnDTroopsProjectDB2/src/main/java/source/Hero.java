package source;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@Entity
public class Hero {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Race race;
    private long age;
    private long rank;
    @ManyToMany
    private List<Role> role;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Troop troop;
    
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public long getAge() {
        return age;
    }

    public long getRank() {
        return rank;
    }

    public List<Role> getRole() {
        return role;
    }

    public Troop getTroop() {
        return troop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
    
    
}
