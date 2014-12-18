package cz.fi.muni.pa165.api.dto;

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
    
}
