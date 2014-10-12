package source;

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

@Entity
public class Troop {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    private Mission mission;
    private int amountOfMoney;
    @OneToMany(mappedBy = "troop")
    private List<Hero> heroes;
    
    protected Troop(){}
    
    public Troop(String name, Hero hero){
        this.name = name;
        heroes.add(hero);
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Mission getMission() {
        return mission;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
    
    
}
