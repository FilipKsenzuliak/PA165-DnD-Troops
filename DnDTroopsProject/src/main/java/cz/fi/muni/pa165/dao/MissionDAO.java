package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Mission;
import java.util.List;

/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco: 324662
 */

public interface MissionDAO {
    
     /**
     * Add new m into database. Id is automatically generated and
     * stored into id attribute.
     *
     * @param mission mission to be created.
     * @throws IllegalArgumentException when mission is null, mission already has id assigned
     */
    public void createMission(Mission mission) throws IllegalArgumentException;
    
    /**
     * Return mission with given id.
     *
     * @param id primary key of mission.
     * @return mission with given id or null if such hero is not in database.
     * @throws IllegalArgumentException when given id is null.
     */
    public Mission getMissionById(Long id) throws IllegalArgumentException;
    
    /**
     * Updates mission in database.
     *
     * @param mission mission to be updated.
     * @throws IllegalArgumentException when mission is null, or has null id.
     */
    public void updateMission(Mission mission) throws IllegalArgumentException;
    
    /**
     * Remove mission from database.
     *
     * @param mission mission to be removed from database.
     * @throws IllegalArgumentException when mission is null, or has null id.
     */
    public void deleteMission(Mission mission) throws IllegalArgumentException;
    
    /**
     * Return list of all missions in the database.
     *
     * @return list of all missions.
     */
    public List<Mission> getAllMissions();
    
}
