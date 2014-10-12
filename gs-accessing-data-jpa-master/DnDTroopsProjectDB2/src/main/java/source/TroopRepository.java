package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TroopRepository extends CrudRepository<Troop, Long> {

    List<Troop> findByName(String name);
}
