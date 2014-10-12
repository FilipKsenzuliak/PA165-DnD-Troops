package source;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * @author David Hubac
 * @uco 396042
 */
@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;        
    private String name;    
    private String description;
    @ManyToMany
    private List<Hero> heroes;
    private int roleLevel = 1;
    
    public Role(String name, String description, int roleLevel){
        this.name=name;
        this.description=description;
        this.roleLevel=roleLevel;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + this.roleLevel;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.roleLevel != other.roleLevel) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", description=" + description + ", roleLevel=" + roleLevel + '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }
    
    
}
