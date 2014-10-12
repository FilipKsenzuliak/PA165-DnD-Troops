package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {

    
    /**
     * Method to find by name.
     * 
     * @param name name to find hero
     * @return list of all heroes with given name.
     */
    List<Hero> findByName(String name);
}
