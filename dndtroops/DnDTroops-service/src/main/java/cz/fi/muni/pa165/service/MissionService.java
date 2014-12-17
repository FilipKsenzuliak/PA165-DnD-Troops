package cz.fi.muni.pa165.service;

import java.util.List;
import cz.fi.muni.pa165.dto.MissionDTO;

/**
 *
 * @author Tomus
 */
public interface MissionService {
    /**
     * Adds mission to DB.
     * @param mission 
     */
    void createMission(MissionDTO mission);
    /**
     * Updates mission's values in DB
     * @param mission 
     */
    void updateMission(MissionDTO mission);  
    /**
     * Deletes mission from DB
     * @param mission 
     */
    void deleteMission(MissionDTO mission);   
    /**
     * Deletes all mission in DB.
     */
    void deleteAllMissions();    
    /**
     * Updates mission from list.
     * @param mission
     */
    void updateMissions(List<MissionDTO> mission);
    /**
     * Gets all mission loaded in DB
     * @return List of mission
     */
    List<MissionDTO> getAllMissions();  
    /**
     * Finds mission by its Id
     * @param id
     * @return mission with specific id
     */
    MissionDTO getMissionById(Long id);
    /**
     * Finds mission by its Id
     * @param name
     * @return mission with specific name
     */
    List<MissionDTO> getMissionByName(String name);
}
