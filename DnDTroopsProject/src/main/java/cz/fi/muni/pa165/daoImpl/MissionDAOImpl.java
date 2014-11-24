package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.MissionDAO;
import cz.fi.muni.pa165.entity.Mission;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco 324662
 */
@Repository
public class MissionDAOImpl implements MissionDAO{

    @Autowired
    EntityManager entityManager;
    
    public MissionDAOImpl(){
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void createMission(Mission mission) throws IllegalArgumentException {
        if(mission == null)
        {
            throw new IllegalArgumentException("Mission cannot be null.");
        }
        if(mission.getId() != null)
        {
            throw new IllegalArgumentException("Mission identifier is already set.");
        }
        entityManager.persist(mission);
    }

    @Override
    public Mission getMissionById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should not be null.");
        }
        Mission mission = entityManager.find(Mission.class, id);
        return mission;
    }

    @Override
    public void updateMission(Mission mission) throws IllegalArgumentException {
        if(mission == null) {
            throw new IllegalArgumentException("Mission can't be null.");
        }
        if(mission.getId() == null) {
            throw new IllegalArgumentException("Mission is not present in DB.");
        }     
        entityManager.merge(mission);
    }

    @Override
    public void deleteMission(Mission mission) throws IllegalArgumentException {
        if(mission == null) {
            throw new IllegalArgumentException("Mission can't be null.");
        }
        if(mission.getId() == null) {
            throw new IllegalArgumentException("Mission is not present in DB.");
        }   
        if (entityManager.contains(mission)){
            entityManager.remove(mission);
            
        }else{
            entityManager.remove(entityManager.merge(mission));
        }
    }

    @Override
    public List<Mission> getAllMissions() {
        List<Mission> missions = entityManager.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
        return missions;
    }
    
    @Override
    public List<Mission> findMissionByName(String name) throws IllegalArgumentException {
        List<Mission> mission;
        mission = entityManager.createQuery("SELECT h FROM Mission h WHERE h.name = :name").setParameter("name", name).getResultList();
        
        return mission;
    }
}
