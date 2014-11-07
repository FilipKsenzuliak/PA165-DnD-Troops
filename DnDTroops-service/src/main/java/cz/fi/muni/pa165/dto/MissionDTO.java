package cz.fi.muni.pa165.dto;

/**
 *
 * @author Tomas Javorsky
 */
public class MissionDTO {
    
    private Long id;
    
    private String name;
    
    private String objective;
    
    private int reward ;
    
    public MissionDTO(Long id){
        this(id, null, null, 0); //int nemoze byt null, ak sa kvoli tomuto nieco dojebe tak sorry
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
    
}
