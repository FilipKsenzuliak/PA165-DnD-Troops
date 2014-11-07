package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.MissionDAO;
import cz.fi.muni.pa165.dto.MissionDTO;
import cz.fi.muni.pa165.entity.Mission;
import cz.fi.muni.pa165.service.MissionService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Tomus
 */
@Service
@Transactional
public class MissionServiceImpl implements MissionService{
    
    @Autowired
    private MissionDAO missionDAO;
    
    private Mapper mapper = new DozerBeanMapper();
    
    public MissionDAO getMissionDAO(){
        return missionDAO;
    }

    public void setMissionDAO(MissionDAO missionDAO) {
        this.missionDAO = missionDAO;
    }
        
    @Override
    public void createMission(MissionDTO mission) {
        Validate.notNull(mission, "Argument is null (on Create)");
        Mission createdMission = mapper.map(mission, Mission.class);
        missionDAO.createMission(createdMission);
        mission.setId(mission.getId());
    }

    @Override
    public void updateMission(MissionDTO mission) {
        Validate.notNull(mission, "Argument is null (on Update)");
        missionDAO.updateMission(mapper.map(mission, Mission.class));
    }
    
    @Override
    public void deleteMission(MissionDTO mission) {
        Validate.notNull(mission, "Argument is null (on delete)");
        
        missionDAO.deleteMission(mapper.map(mission, Mission.class));
   }

    @Override
    public void deleteAllMissions() {
        List<Mission> missions = missionDAO.getAllMissions();
        for(Mission m : missions){
            missionDAO.deleteMission(m);
        }
    }

    @Override
    public void updateMissions(List<MissionDTO> missions) {
        Validate.notNull(missions, "Argument is null. (Mass update)");
        
        for(MissionDTO mission : missions){
            missionDAO.updateMission(mapper.map(mission, Mission.class));
        }
    }

    @Override
    public List<MissionDTO> getAllMissions() {
        List<MissionDTO> missionsDTO = new ArrayList<MissionDTO>();
        for(Mission mission : missionDAO.getAllMissions()){
            missionsDTO.add(mapper.map(mission, MissionDTO.class));
        }
        return missionsDTO;
    }

    @Override
    public MissionDTO getMissionById(Long id) {
        Validate.isTrue(id>0, "Invalid ID (get mission by id)");
        Validate.isTrue(id != null, "ID is null (get mission by id)");
        
        MissionDTO mission = mapper.map(missionDAO.getMissionById(id), MissionDTO.class);
        return mission;
    }

    @Override
    public List<MissionDTO> getMissionByName(String name) {
        Validate.isTrue(name.isEmpty(),"name is empty (find mission by name)");
        
        List<MissionDTO> missions = new ArrayList<MissionDTO>();
        try{
            missions.add(mapper.map(missionDAO.findMissionByName(name),MissionDTO.class));
        }
        catch(Exception e){
            throw new DataAccessException("persistance error"){};
        }
        return missions;
    }
    
}
