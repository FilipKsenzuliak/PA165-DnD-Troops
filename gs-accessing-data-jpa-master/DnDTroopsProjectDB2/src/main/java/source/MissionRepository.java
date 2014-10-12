package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {
    /**
     * Method to find by name.
     * 
     * @param name name to find mission
     * @return list of all missions with given name.
     */
    List<Mission> findByName(String name);
}
