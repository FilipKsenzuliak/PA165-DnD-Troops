package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TroopRepository extends CrudRepository<Troop, Long> {
    
    /**
     * Method to find by name.
     * 
     * @param name name to find troop
     * @return list of all troops with given name.
     */
    List<Troop> findByName(String name);
}
