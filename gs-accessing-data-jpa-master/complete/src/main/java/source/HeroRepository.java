package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {

    List<Hero> findByName(String name);
}
