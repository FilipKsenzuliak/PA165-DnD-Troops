package cz.fi.muni.pa165.dto;

import java.util.Objects;

/**
 *
 * @author Tomas Javorsky
 */
public class MissionDTO {
    
    private Long id;
    
    private String name;
    
    private String objective;
    
    private int reward ;
    
    public MissionDTO(){
        
    }
    
    public MissionDTO(String name, String objective, int reward){
        this(-1L, name, objective, reward);
    }
    
    public MissionDTO(Long id, String name, String objective, int reward){
        this.id = id;
        this.name = name;
        this.objective = objective;
        this.reward = reward;
    }
    
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
    
    public void setId(Long id){
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.objective);
        hash = 73 * hash + this.reward;
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
        final MissionDTO other = (MissionDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.objective, other.objective)) {
            return false;
        }
        return true;
    }
    
}
