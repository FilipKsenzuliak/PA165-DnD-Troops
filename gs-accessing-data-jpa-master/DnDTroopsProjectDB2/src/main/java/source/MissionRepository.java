package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {

    List<Mission> findByName(String name);
}
