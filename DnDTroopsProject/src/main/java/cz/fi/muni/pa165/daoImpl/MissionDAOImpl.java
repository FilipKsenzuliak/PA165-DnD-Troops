package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.MissionDAO;
import cz.fi.muni.pa165.entity.Mission;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco 324662
 */
public class MissionDAOImpl implements MissionDAO{

    private EntityManagerFactory emf;
    
    public MissionDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mission);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Mission getMissionById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should not be null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mission mission = em.find(Mission.class, id);
        em.getTransaction().commit();
        em.close();
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();   
        em.merge(mission);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteMission(Mission mission) throws IllegalArgumentException {
        if(mission == null) {
            throw new IllegalArgumentException("Mission can't be null.");
        }
        if(mission.getId() == null) {
            throw new IllegalArgumentException("Mission is not present in DB.");
        } 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();   
        if (em.contains(mission)){
            em.remove(mission);
        }else{
            em.remove(em.merge(mission));
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Mission> getAllMissions() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Mission> missions = em.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return missions;
    }
    
    @Override
    public List<Mission> findMissionByName(String name) throws IllegalArgumentException {
        List<Mission> mission;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        mission = em.createQuery("SELECT h FROM Mission h WHERE h.name = :name").setParameter("name", name).getResultList();
        em.getTransaction().commit();
        em.close();
        return mission;
    }
}
