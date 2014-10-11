package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Mission;
import java.util.List;

/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * uco: 324662
 */

public interface MissionDAO {
    
    public void createMission(Mission mission) throws IllegalArgumentException;
    
    public Mission getMissionById(Long id) throws IllegalArgumentException;
    
    public void updateMission(Mission mission) throws IllegalArgumentException;
    
    public void deleteMission(Mission mission) throws IllegalArgumentException;
    
    public List<Mission> getAllMissions();
    
}
